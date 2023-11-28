package com.example.instagramclonecoding.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

//    private final UserRepository userRepository;
    private final Tokenizer tokenizer;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication.getCredentials())
                .filter(Objects::nonNull)
                .cast(String.class)
                .flatMap(token -> {
                    if (tokenizer.verify(token)) {
                        return Mono.error(RuntimeException::new); // .
                    }
                    return tokenizer.getAuthentication(token);
                }).onErrorResume(e -> {
                    if (e instanceof JwtException) {
                        return Mono.empty();
                    } else {
                        return Mono.error(e);
                    }
                });

//        return Mono.justOrEmpty(authentication)
//                .filter(Objects::nonNull)
//                .map(Authentication::getName)
//                .flatMap(userRepository::findById)
//                .switchIfEmpty(Mono.defer(Mono::empty)) // 유저를 찾지 못했을 때
//                .flatMap(user -> Mono.just(authentication))
//                .onErrorResume(e -> {
//                    if (e instanceof JwtException) {
//                        return Mono.empty();
//                    } else {
//                        return Mono.error(e);
//                    }
//                });
    }

}
