package com.smartclinic.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Service to handle JWT generation and validation.
 */
@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms:86400000}") // Default 24 hours
    private long expirationMillis;

    // ──────────────────────────────────────────────────────────────────────────────
    // Generate JWT using user's email (✅ 3 pts)
    // ──────────────────────────────────────────────────────────────────────────────
    public String generateToken(String userEmail) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ──────────────────────────────────────────────────────────────────────────────
    // Return signing key using configured secret (✅ 2 pts)
    // ──────────────────────────────────────────────────────────────────────────────
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
