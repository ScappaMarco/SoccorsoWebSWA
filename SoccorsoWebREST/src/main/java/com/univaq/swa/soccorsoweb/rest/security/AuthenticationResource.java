package com.univaq.swa.soccorsoweb.rest.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("auth") //[BASE]/auth/...
public class AuthenticationResource {

    /*
    RIROSRA AUTENTICAZIONE:
        - auth/login
        - auth/logout
        - auth/refresh
     */

    @POST
    @Path("login") // POST [BASE]/auth/login
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN) // AUTH TOKEN
        // 200 - {AUTH TOKEN}
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
                System.out.println("SONO QUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");

                return Response.ok().header(AUTHORIZATION, "Bearer " + authToken).build();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @Path("logout") //DELETE [BASE]/auth/logout
    @Logged //CUSTOM LABEL
    @Operation(description = "Logout by removing the current authentication token",
                tags = {"Authentication"}, security = @SecurityRequirement(name = "bearerAuth"),
                    responses = {
                        @ApiResponse(responseCode = "204", description = "Successfull logout"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized"),
                        @ApiResponse(responseCode = "500", description = "General error")
                    })
    public Response logout(@Context ContainerRequestContext creq) {
        String token = (String) creq.getProperty("token");
        AuthHelpers.getInstance().revokeToken(token);
        return Response.noContent().cookie(new NewCookie.Builder("token").value("").maxAge(0).build()).build();
    }

    @GET
    @Path("refresh") //GET [BASE]/auth/refresh
    @Produces(MediaType.TEXT_PLAIN) //NEW AUTH TOKEN
        // 200 - {"NEW AUTH TOKEN: BEARER ...}
    @Logged
    @Operation(description = "Refresh the auth token", tags = {"Authentication"},
                security = @SecurityRequirement(name = "bearerAuth"),
                    responses = {
                        @ApiResponse(responseCode = "200", description = "The new auth token",
                                    content = @Content(schema = @Schema(format = "text/plain", type = "string")),
                                    headers = {
                                        @Header(
                                                name = "Authorization",
                                                description = "Token",
                                                schema = @Schema(type = "string", pattern = "Bearer [a-z0-9A-Z]+")
                                        )
                                    }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized"),
                        @ApiResponse(responseCode = "500", description = "General error")
                    })
    public Response refresh(@Context ContainerRequestContext creq, UriInfo ui) {
        String username = (String) creq.getProperty("user");
        String newToken = AuthHelpers.getInstance().issueToken(ui, username);

        return  Response.ok().header(AUTHORIZATION, "Bearer " + newToken).build();
    }
}