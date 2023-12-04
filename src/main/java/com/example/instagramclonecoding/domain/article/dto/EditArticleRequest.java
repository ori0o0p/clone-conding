package com.example.instagramclonecoding.domain.article.dto;

import lombok.NonNull;

public record EditArticleRequest(
        @NonNull String id,
        String content
) {
}
