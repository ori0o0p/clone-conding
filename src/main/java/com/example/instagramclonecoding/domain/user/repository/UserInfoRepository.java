package com.example.instagramclonecoding.domain.user.repository;

import com.example.instagramclonecoding.domain.user.entity.UserInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserInfoRepository extends ReactiveMongoRepository<UserInfo, String> {
}
