package com.example.instagramclonecoding.domain.user.repository;

import com.example.instagramclonecoding.domain.user.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<Boolean> existsByEmail(String email);
    Mono<User> findByEmail(String email);
}
