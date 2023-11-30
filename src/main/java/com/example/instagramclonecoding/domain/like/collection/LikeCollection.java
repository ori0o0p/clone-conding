package com.example.instagramclonecoding.domain.like.collection;

import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import com.example.instagramclonecoding.domain.like.entity.Like;
import reactor.core.publisher.Flux;

import java.util.List;

public class LikeCollection {
    private final List<Like> likeList;

    public LikeCollection(List<Like> likeList) {
        this.likeList = likeList;
    }

    public List<LikeResponse> toLikeResponse() {
        return likeList.stream()
                .map(LikeResponse::new)
                .toList();
    }

}
