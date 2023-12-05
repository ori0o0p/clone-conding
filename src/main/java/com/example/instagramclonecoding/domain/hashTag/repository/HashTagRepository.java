package com.example.instagramclonecoding.domain.hashTag.repository;

import com.example.instagramclonecoding.domain.hashTag.entity.HashTag;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HashTagRepository extends ReactiveMongoRepository<HashTag, String> {
}
