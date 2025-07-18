package com.univaq.swa.soccorsoweb.rest.resources;

/*
Questa non è una risorsa "standard": questa risorsa risponde al path [BASE]/requests/{îd}/...
 e i metodi qui dentro vengono chiamati a partire dalla risorsa concettualmente sopra: HelpRequestsResource che
 al suo interno avrà un metodo che risponde al path [BASE]/requests/{id} e che reindirizzerà a questa sotto-risorsa
 (notare l'assenza dell'annotazione "Path"
*/

import com.univaq.swa.soccorsoweb.rest.business.factory.HelpRequestServiceFactory;
import com.univaq.swa.soccorsoweb.rest.business.service.HelpRequestService;
import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;
import com.univaq.swa.soccorsoweb.rest.model.Mission;
import com.univaq.swa.soccorsoweb.rest.security.Logged;
import com.univaq.swa.soccorsoweb.rest.utils.DummyGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javassist.NotFoundException;

public class HelpRequestResource {
    private final HelpRequestService helpRequestService;
    private final HelpRequest helpRequest;

    public HelpRequestResource(HelpRequest hr) {
        this.helpRequestService = HelpRequestServiceFactory.getHelpRequestService();
        this.helpRequest = hr;
    }

    @GET //GET [BASE]/requests/{id}
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    @Operation(description = "This operartion get the specified help request", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                responses = {
                    @ApiResponse(responseCode = "200", description = "The specified help request",
                        content = @Content(schema = @Schema(implementation = HelpRequest.class))),
                    @ApiResponse(responseCode = "404", description = "Help request not found")
                })
    public Response getHelpRequest() {
        return Response.ok(helpRequest).build();
    }

    @DELETE // DELETE [BASE]/requests/{id}
    @Logged
    @Operation(description = "This operation deletes the specified help request", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
                })
    public Response deleteHelpRequest() {
        try {
            helpRequestService.deleteRequest(helpRequest.getId());
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Help request not found").build();
        }
    }

    @GET
    @Path("/mission") //GET [BASE]/requests/{id}/mission
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    @Operation(description = "This operation gets the mission of the specified request", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                responses = {
                    @ApiResponse(responseCode = "200", description = "The mission of the specified request",
                        content = @Content(schema = @Schema(implementation = Mission.class))),
                    @ApiResponse(responseCode = "404", description = "Help request not found"),
                    @ApiResponse(responseCode = "500", description = "General error")
                })
    public Response getRequestMission() {
        try {
            Mission mission = helpRequestService.getMissione(helpRequest.getId());
            if(mission == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("This help request does not have an associated mission").build();
            }
            return Response.ok(mission).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Help request not found").build();
        }
    }

    @PATCH
    @Path("/validate") //PATCH [BASE]/requests/{id}/validate
    @Logged
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "This operation update the specified help request setting the status filed to active", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                requestBody = @RequestBody(
                        required = true,
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(type = "string", example = "{\"status\":\"active\"}")
                        )
                ),
                responses = {
                    @ApiResponse(responseCode = "201", description = "Request validated"),
                    @ApiResponse(responseCode = "404", description = "Request not found")
                })
    public Response validateRequest() {
        try {
            this.helpRequestService.validateRequest(helpRequest.getId());
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Help request not found").build();
        }
    }

    @PATCH
    @Path("/close")
    @Logged
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "This operation set the request status to closed", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                requestBody = @RequestBody(
                        required = true,
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(type = "string", example = "{\"status\":\"closed\"}")
                        )
                ),
                responses = {
                    @ApiResponse(responseCode = "201", description = "Mission ended") ,
                    @ApiResponse(responseCode = "404", description = "Help request not found")
                })
    public Response closeRequest() {
        try {
            this.helpRequestService.closeHelpRequest(helpRequest.getId());
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Help request not found").build();
        }
    }

    @PATCH
    @Path("/mission")
    @Logged
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "This operation set the mission filed of the specified request", tags = {"HelpRequest"},
                security = @SecurityRequirement(name = "bearerAuth"),
                requestBody = @RequestBody(
                        required = true,
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = Mission.class)
                        )
                ),
                responses = {
                    @ApiResponse(responseCode = "201", description = "Mission created"),
                        @ApiResponse(responseCode = "404", description = "Not found"),
                        @ApiResponse(responseCode = "500", description = "General error")
                })
    public Response startMission() {
        try {
            if(helpRequest.getMissione() != null) {
                this.helpRequestService.setMission(helpRequest.getId(), DummyGenerator.generateDummyOngoingMission());
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Mission alredy exiting").build();
            }
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Help request not found").build();
        }
    }
}