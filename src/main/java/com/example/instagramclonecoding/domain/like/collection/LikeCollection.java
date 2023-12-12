package com.example.instagramclonecoding.domain.like.collection;

import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import com.example.instagramclonecoding.domain.like.entity.Like;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

public class LikeCollection {
    private final List<Like> likeList;

    public LikeCollection(List<Like> likeList) {
        this.likeList = likeList;
    }

    public Flux<LikeResponse> toLikeResponse() {
        return Flux.fromIterable(likeList)
                .filter(Objects::nonNull)
                .map(LikeResponse::new);
    }
}
