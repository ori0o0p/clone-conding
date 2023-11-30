package com.example.instagramclonecoding.domain.like.dto;

import com.example.instagramclonecoding.domain.like.entity.Like;
import com.example.instagramclonecoding.domain.user.entity.User;
import lombok.Builder;

@Builder
public record LikeResponse(
        User user
//        Article article
) {
    public LikeResponse(Like like) {
        this(like.getUser());
    }
}
