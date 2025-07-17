package com.univaq.swa.soccorsoweb.rest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.ws.rs.core.UriInfo;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class JWTHelpers {
    private static JWTHelpers instance = null;
    private SecretKey key = null;

    private JWTHelpers() {
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance("HmacSha256");
            key = kg.generateKey();
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }

    public SecretKey getJWTKey() {
        return key;
    }

    public String validateToken(String token) {
        Jws<Claims> jwsc = Jwts.parser().verifyWith(getJWTKey()).build().parseSignedClaims(token);
        return jwsc.getPayload().getSubject();
    }

    public String issueToken(UriInfo cx, String username) {
        String token = Jwts.builder()
                .subject(username)
                .issuer(cx.getAbsolutePath().toString())
                .issuedAt(new Date())
                .expiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getJWTKey())
                .compact();
        return token;
    }

    public void revokeToken(String token) {
        // revoke token TODO
    }

    public static JWTHelpers getInstance() {
        if(instance == null) instance = new JWTHelpers();
        return instance;
    }
}