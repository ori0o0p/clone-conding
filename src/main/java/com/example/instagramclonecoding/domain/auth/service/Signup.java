package com.example.instagramclonecoding.domain.auth.service;

import com.example.instagramclonecoding.domain.auth.dto.request.SignupRequest;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class Signup {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Signup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<Void> execute(SignupRequest request) {
        return userRepository.findByEmail(request.email())
                .flatMap(existingUser -> Mono.error(new RuntimeException("유저가 이미 존재")))
                .switchIfEmpty(Mono.defer(() -> {
                    final String encodedPassword = passwordEncoder.encode(request.password());
                    User newUser = User.builder()
                            .email(request.email())
                            .name(request.name())
                            .password(encodedPassword)
                            .gender(request.gender())
                            .build();
                    return userRepository.save(newUser);
                })).then();
    }

}
