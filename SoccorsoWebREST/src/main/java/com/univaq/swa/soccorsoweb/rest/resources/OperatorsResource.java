package com.univaq.swa.soccorsoweb.rest.resources;

import com.univaq.swa.soccorsoweb.rest.business.factory.OperatorServiceFactory;
import com.univaq.swa.soccorsoweb.rest.business.service.OperatorService;
import com.univaq.swa.soccorsoweb.rest.model.Operator;
import com.univaq.swa.soccorsoweb.rest.security.Logged;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import javassist.NotFoundException;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Path("/operators")
public class OperatorsResource {
    private final OperatorService operatorService;

    public OperatorsResource() {
        this.operatorService = OperatorServiceFactory.getOperatorService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Logged
    @Operation(description = "This operation adds an operator", tags = {"Operator"},
                security = @SecurityRequirement(name = "bearerAuth"),
                requestBody = @RequestBody(
                        required = true,
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = Operator.class)
                        )
                ),
                responses = {
                    @ApiResponse(responseCode = "201", description = "Operator added",
                            content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(implementation = URI.class)))
                })
    public Response addOperator(
            @Parameter(description = "The operator to add", schema = @Schema(implementation = Operator.class), required = true) Operator operator,
            @Context UriInfo uriInfo) {

        String oid = String.valueOf(operatorService.addOperator(operator));
        URI uri = uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getOperator").build(oid);

        return Response.created(uri).entity(uri.toString()).build();
    }

    @GET
    @Path("/{status: [a-z]+}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    @Operation(description = "This operation gets all the operators in the specified status", tags = {"Operator"},
                security = @SecurityRequirement(name = "bearerAuth"),
                requestBody = @RequestBody(
                        required = true,
                        content = @Content(
                                mediaType = MediaType.TEXT_PLAIN,
                                schema = @Schema(type = "string", example = "free")
                        )
                ),
                responses = {
                    @ApiResponse(responseCode = "200", description = "List of operators in tha specified status",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Operator.class)))
                })
    public Response getOperatorsPerStatus(String status, @Context UriInfo uriInfo) {
        return Response.ok(mapOperator(operatorService.getOperatorPerStatus(status), uriInfo)).build();
    }

    @Path("/{id: [0-9]+}")
    public OperatorResource getOperator(@PathParam("id") int operatorId) {
        try {
            Operator op = operatorService.getOperator(operatorId);
            return new OperatorResource(op);
        } catch (NotFoundException e) {
            return null;
        }
    }

    public List<Map<String, Object>> mapOperator(List<Operator> operatorList, UriInfo uriInfo) {
        return operatorList.stream().map(operator -> {
            URI uri = uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getOperator").build(operator.getId());
            return Map.of("Operator", operator, "URL", uri);
        }).toList();
    }
}