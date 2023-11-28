package com.example.instagramclonecoding.domain.follow.repository;

import com.example.instagramclonecoding.domain.follow.entity.Follow;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface FollowRepository extends ReactiveMongoRepository<Follow, String> {
    Mono<Follow> findByUserId(String id);
}
