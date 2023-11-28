package com.example.instagramclonecoding.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

@Component
public class Tokenizer {

    private static final String SECRET = "secretkey";

    private static final int EXP = 2;

    public String createAccessToken(String user) {
        return tokenize(user, "accessToken");
    }

    public String createRefreshToken(String user) {
        return tokenize(user, "refreshToken");
        // save
    }

    private String tokenize(String user, String type) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, EXP);
        Date expiresAt = calendar.getTime();

        Claims claims = Jwts.claims()
                .setSubject(user);
        claims.put("type", type);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("*^^*")
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean verify(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token).getBody();
    }

    public UserDetails createAuthenticatedUserFromClaims(Claims claims) {
        String subject = claims.getSubject();
        return new User(subject, "", Collections.emptyList());
    }

    public Mono<Authentication> getAuthentication(String token) {
        Claims claims = parseClaims(token);
        UserDetails details = createAuthenticatedUserFromClaims(claims);
        return Mono.just(new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()));
    }

}
