package com.univaq.swa.soccorsoweb.rest.security;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.io.IOException;
import java.security.Principal;

@Provider
@Logged
@Priority(Priorities.AUTHORIZATION)
public class AuthLoggedFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = null;
        final String path = requestContext.getUriInfo().getAbsolutePath().toString();

        String authorizationHeader = requestContext.getHeaderString(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring("Bearer".length()).trim();
        }
        if(token != null && token .isEmpty()) {
            try {
                final String username = AuthHelpers.getInstance().validateToken(token);
                if(username != null) {
                    requestContext.setProperty("token", token);
                    requestContext.setProperty("user", username);

                    requestContext.setSecurityContext(new SecurityContext() {
                        @Override
                        public Principal getUserPrincipal() {
                            return new Principal() {
                                @Override
                                public String getName() {
                                    return username;
                                }
                            };
                        }

                        @Override
                        public boolean isUserInRole(String role) {
                            //...
                            return true;
                        }

                        @Override
                        public boolean isSecure() {
                            return path.startsWith("https");
                        }

                        @Override
                        public String getAuthenticationScheme() {
                            return "Token-Based-Auth-Scheme";
                        }
                    });
                } else {
                    requestContext.abortWith(Response.status(UNAUTHORIZED).build());
                }
            } catch (Exception e) {
                requestContext.abortWith(Response.status(UNAUTHORIZED).build());
            }
        } else {
            requestContext.abortWith(Response.status(UNAUTHORIZED).build());
        }
    }
}