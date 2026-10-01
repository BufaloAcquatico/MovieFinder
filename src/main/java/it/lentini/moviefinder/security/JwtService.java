package it.lentini.moviefinder.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.lentini.moviefinder.config.JwtConfig;
import it.lentini.moviefinder.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtConfig config;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(config.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        return generateToken(user, config.getAccessTokenExpiration());
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, config.getRefreshTokenExpiration());
    }

    private String generateToken(User user, long tokenExpiration) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis() + 1000 * tokenExpiration
                        )
                )
                .signWith(key)
                .compact();
    }

    public String extractSubject(String token) {
        return extractAllClaims(token).getSubject();
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(config.getSecret().getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload().getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }
}