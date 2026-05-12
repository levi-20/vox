package com.cs.vox.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry}")
    private long expiry;

    public String generateToken(String username) {

        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expiry))
            .signWith(getSigningKey())
            .compact();
    }

    public boolean validateToken(String token) {
        try {

            return extractClaims(token) != null;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUser(String token) {

        try {
            return extractClaims(token).getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    private Claims extractClaims(String token) {

        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }
}
