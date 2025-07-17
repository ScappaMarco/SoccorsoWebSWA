package com.univaq.swa.soccorsoweb.rest.security;

import jakarta.ws.rs.core.UriInfo;

public class AuthHelpers {
    private static AuthHelpers instance = null;
    private static JWTHelpers jwtHelpers;

    private AuthHelpers() {
        jwtHelpers = JWTHelpers.getInstance();
    }

    public boolean authenticateUser(String username, String password) {
        return true;
    }

    public String issueToken(UriInfo cx, String username) {
        return jwtHelpers.issueToken(cx, username);
    }

    public void revokeToken(String token) {
        jwtHelpers.revokeToken(token);
    }

    public String validateToken(String token) {
        return jwtHelpers.validateToken(token);
    }

    public static AuthHelpers getInstance() {
        if(instance == null) instance = new AuthHelpers();
        return instance;
    }
}