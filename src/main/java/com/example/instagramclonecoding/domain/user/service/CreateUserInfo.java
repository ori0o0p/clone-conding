package com.example.instagramclonecoding.domain.user.service;

import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.entity.UserInfo;
import com.example.instagramclonecoding.domain.user.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateUserInfo {
    private final UserInfoRepository userInfoRepository;

    public CreateUserInfo(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public Mono<Void> execute(User user) {
        return userInfoRepository.save(UserInfo.builder()
                .user(user)
                .build()).then();
    }

}
