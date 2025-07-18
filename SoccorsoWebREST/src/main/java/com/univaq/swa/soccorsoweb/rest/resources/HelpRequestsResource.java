package com.univaq.swa.soccorsoweb.rest.resources;

/*
Questa è la risorsa HelpRequest principale: i metodi in questa classe rispondono al path [BASE]/requests/...
All'interno di questa classe è presente anche una "chiamata" a una sotto-risorsa, la quale risponde al path [BASE]/requests/{id}
 */

import com.univaq.swa.soccorsoweb.rest.business.factory.HelpRequestServiceFactory;
import com.univaq.swa.soccorsoweb.rest.business.service.HelpRequestService;
import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;
import com.univaq.swa.soccorsoweb.rest.security.Logged;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;
import javassist.NotFoundException;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Path("/requests")
public class HelpRequestsResource {
    private final HelpRequestService helpRequestService;

    public HelpRequestsResource() {
        this.helpRequestService = HelpRequestServiceFactory.getHelpRequestService();
    }

    @POST //POST [BASE]/requests
    @Consumes(MediaType.APPLICATION_JSON) //oggetto json di richiesta
    @Produces(MediaType.TEXT_PLAIN) //restituisce il link alla richiesta appena creata
    @Logged
    @Operation(description = "This operations adds an help request", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                responses = {
                    @ApiResponse(
                            responseCode = "201", description = "Help request added",
                            content = @Content(schema = @Schema(implementation = URI.class))
                    ),
                })
    public Response addRequest(
            @Parameter(description = "The help request to add", schema = @Schema(implementation = HelpRequest.class), required = true) HelpRequest helpRequest,
            @Context SecurityContext securityContext, @Context ContainerRequestContext requestContext,
            @Context UriInfo uriInfo) {

        //prendiamo l'id della richiesta appena inserita per poi restituirlo nella Response
        String hrid = String.valueOf(helpRequestService.addRequest(helpRequest));
        URI uri = uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getHelpRequest").build(hrid);

        return Response.created(uri).entity(uri.toString()).build();
    }

    @GET
    @Path("/closed")
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    @Operation(description = "This operation gets all the closed requests", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                responses = {
                    @ApiResponse(responseCode = "200", description = "All the closed help requests",
                        content = @Content(schema = @Schema(implementation = HelpRequest.class)))
                })
    public Response getClosedHelpRequests(@Context SecurityContext securityContext, @Context ContainerRequestContext containerRequestContext,
                                          @Context UriInfo uriInfo) {
        return Response.ok(mapHelpRequests(helpRequestService.getClosedHelpRequests(), uriInfo)).build();
        
    }

    @Path("/{id: [0-9]+}")
    public HelpRequestResource getHelpRequest(@PathParam("id") int helpRequestId) {
        try {
            HelpRequest hr = helpRequestService.getHelpRequest(helpRequestId);
            return new HelpRequestResource(hr);
        } catch (NotFoundException e) {
            return null;
        }
    }

    private List<Map<String, Object>> mapHelpRequests(List<HelpRequest> helpRequestList, UriInfo uriInfo) {
        return helpRequestList.stream().map(request -> {
            URI uri = uriInfo.getBaseUriBuilder().path(getClass()).path(getClass(), "getHelpRequest").build(request.getId());
            return Map.of("helpRequest", request, "URL", uri);
        }).toList();
    }
}