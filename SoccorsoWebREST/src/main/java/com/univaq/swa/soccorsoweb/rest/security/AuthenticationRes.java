package com.univaq.swa.soccorsoweb.rest.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("auth")
public class AuthenticationRes {

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
        @Operation(description = "Login by Username and Password",
                    tags = {"Authentication"},
                    responses = {
                        @ApiResponse(responseCode = "200", description = "The auth token",
                                        content = @Content(schema = @Schema(type = "string")),
                                        headers = {
                                            @Header(
                                                    name = "Authorization",
                                                    description = "Bearer token",
                                                    schema = @Schema(type = "string", pattern = "Bearer [a-z0-9A-Z]+")
                                            )
                                        }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized"),
                        @ApiResponse(responseCode = "500", description = "Username or password wrong")
                    })
    public Response login(@Context UriInfo uriInfo,
                          @Parameter(schema = @Schema(type = "string"))
                          @FormParam("username") String username,
                          @Parameter(schema = @Schema(type = "string"))
                          @FormParam("password") String password) {
        try {
            if(AuthHelpers.getInstance().authenticateUser(username, password)) {
                String authToken = AuthHelpers.getInstance().issueToken(uriInfo, username);

                return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


}
