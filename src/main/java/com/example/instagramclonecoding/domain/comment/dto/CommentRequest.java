package com.example.instagramclonecoding.domain.comment.dto;

import lombok.NonNull;

public record CommentRequest(
        @NonNull
        String articleId,
        String content
) {
}
