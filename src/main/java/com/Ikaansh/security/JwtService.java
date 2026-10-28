package com.Ikaansh.security;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "SecureApiAssignmentSecretKey2026ForJwtSigning123456";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60; // 1 hour

    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    public void validateSecret() {
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalStateException(
                    "JWT secret must contain at least 32 characters");
        }
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token, String email) {
        try {
            return extractEmail(token).equals(email);
        } catch (io.jsonwebtoken.JwtException
                | IllegalArgumentException exception) {
            return false;
        }
    }
}
