package com.Cat.sCorner.Cat.sCorner.config;

import com.Cat.sCorner.Cat.sCorner.entity.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;



import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;

    private final UserDetailsService userDetailsService;

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);



    public String generateToken(UserPrincipal userPrincipal) {

        Date now = new Date();
        long validityInMilliseconds = 3600000L * 24 * 30;
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userPrincipal.getId());

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            String username = getUsername(token);
            log.debug("Username extracted from token for validation: {}", username);
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            log.debug("JWT token is valid");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        log.debug("Username extracted from token: {}", username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            log.debug("Username extracted from token: {}", username);
            return username;
        } catch (Exception e) {
            log.error("Error extracting username from token", e);
            return null;
        }
    }
}
