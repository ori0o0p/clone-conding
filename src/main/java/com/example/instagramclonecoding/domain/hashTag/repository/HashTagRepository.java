package com.example.instagramclonecoding.domain.hashTag.repository;

import com.example.instagramclonecoding.domain.hashTag.entity.HashTag;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface HashTagRepository extends ReactiveMongoRepository<HashTag, String> {

    @Override
    @Query(sort = "{tag : -1}")
    Flux<HashTag> findAll();

}
