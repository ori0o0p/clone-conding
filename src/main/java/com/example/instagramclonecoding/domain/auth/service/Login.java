package com.example.instagramclonecoding.domain.auth.service;

import com.example.instagramclonecoding.domain.auth.dto.request.LoginRequest;
import com.example.instagramclonecoding.domain.auth.dto.response.TokenResponse;
import com.example.instagramclonecoding.domain.user.repository.UserRepository;
import com.example.instagramclonecoding.global.security.jwt.Tokenizer;
import com.example.instagramclonecoding.domain.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class Login {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Tokenizer tokenizer;

    public Login(UserRepository userRepository, PasswordEncoder passwordEncoder, Tokenizer tokenizer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenizer = tokenizer;
    }

    public Mono<TokenResponse> execute(LoginRequest request) {
        return userRepository.findByEmail(request.email())
                .filter(user -> passwordEncoder.matches(request.password(), user.getPassword()))
                .switchIfEmpty(Mono.error(new RuntimeException("비밀번호가 일치하지 않음")))
                .map(User::getEmail)
                .map(userId -> {
                    final String accessToken = tokenizer.createAccessToken(userId);
                    final String refreshToken = tokenizer.createRefreshToken(userId);
                    return TokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                }).onErrorResume(Mono::error);
    }

}
