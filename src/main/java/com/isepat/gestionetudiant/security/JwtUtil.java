package com.isepat.gestionetudiant.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Clé secrète générée au démarrage de l'application (garde-la stable en prod, via variable d'environnement)
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 10; // 10 heures

    public String genererToken(String email, String role) {
        Date maintenant = new Date();
        Date expiration = new Date(maintenant.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(maintenant)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public String extraireEmail(String token) {
        return extraireClaims(token).getSubject();
    }

    public String extraireRole(String token) {
        return extraireClaims(token).get("role", String.class);
    }

    public boolean estValide(String token) {
        try {
            extraireClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims extraireClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}