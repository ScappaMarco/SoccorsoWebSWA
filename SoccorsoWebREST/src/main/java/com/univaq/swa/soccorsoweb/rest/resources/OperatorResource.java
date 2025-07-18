package com.univaq.swa.soccorsoweb.rest.resources;

import com.univaq.swa.soccorsoweb.rest.business.factory.OperatorServiceFactory;
import com.univaq.swa.soccorsoweb.rest.business.service.OperatorService;
import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;
import com.univaq.swa.soccorsoweb.rest.model.Mission;
import com.univaq.swa.soccorsoweb.rest.model.Operator;
import com.univaq.swa.soccorsoweb.rest.security.Logged;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import javassist.NotFoundException;

import java.net.URI;
import java.util.List;
import java.util.Map;

//@Path("/operators/{id}")
public class OperatorResource {
    private final OperatorService operatorService;
    private final Operator operator;

    public OperatorResource(Operator operator) {
        this.operatorService = OperatorServiceFactory.getOperatorService();
        this.operator = operator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    @Operation(description = "This operation gets the specified operator", tags = {"Operator"},
                security = @SecurityRequirement(name = "bearerAuth"),
                responses = {
                    @ApiResponse(responseCode = "200", description = "The specified operator",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Operator.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")
                })
    public Response getOperator() {
        return Response.ok(operator).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    @Path("/missions")
    @Operation(description = "This operation gets all the missions of the specified operator (we will extract the requests)", tags = {"Operator"},
                security = @SecurityRequirement(name = "bearerAuth"),
                responses = {
                    @ApiResponse(responseCode = "200", description = "The list of the mission of the specified operator",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Mission.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")
                })
    public Response getOperatorMissions(@Context UriInfo uriInfo) {
        try {
            return Response.ok(mapRequests(operatorService.getOperatorClosedMission(operator.getId()), uriInfo)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Operator not found").build();
        }
    }

    public List<Map<String, Object>> mapOperator(List<Operator> operatorList, UriInfo uriInfo) {
        return operatorList.stream().map(operator -> {
            URI uri = uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getOperator").build(operator.getId());
            return Map.of("Operator", operator, "URL", uri);
        }).toList();
    }

    public List<Map<String, Object>> mapRequests(List<HelpRequest> helpRequestList, UriInfo uriInfo) {
        return helpRequestList.stream().map(helpRequest -> {
            URI uri = uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getHelpRequest").build(helpRequest.getId());
            return Map.of("helpRequest", helpRequest, "URL", uri);
        }).toList();
    }
}