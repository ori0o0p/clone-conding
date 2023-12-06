package com.example.instagramclonecoding.domain.hashTag.dto;

import com.example.instagramclonecoding.domain.hashTag.entity.HashTag;
import lombok.Builder;

@Builder
public record HashTagResponse(
        String tag
) {

    public HashTagResponse(HashTag hashTag) {
        this(hashTag.getTag());
    }

}
