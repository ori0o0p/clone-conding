package com.example.instagramclonecoding.user.service;

import com.example.instagramclonecoding.security.Tokenizer;
import com.example.instagramclonecoding.user.dto.LoginRequest;
import com.example.instagramclonecoding.user.dto.TokenResponse;
import com.example.instagramclonecoding.user.entity.User;
import com.example.instagramclonecoding.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.security.auth.login.AccountNotFoundException;

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
