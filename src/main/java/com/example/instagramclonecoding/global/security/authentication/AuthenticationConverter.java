package com.example.instagramclonecoding.global.security.authentication;

import com.example.instagramclonecoding.global.security.jwt.Tokenizer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationConverter implements ServerAuthenticationConverter {

    private final Tokenizer tokenizer;

    public AuthenticationConverter(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange)
                .flatMap(this::extractToken)
                .flatMap(this::getAuthentication);
    }

    private Mono<String> extractToken(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION))
                .map(s -> s.substring(7));
    }

    private Mono<Authentication> getAuthentication(String token) {
        return tokenizer.getAuthentication(token);
    }

}
