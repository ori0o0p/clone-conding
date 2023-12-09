package com.example.instagramclonecoding.domain.article.dto;

import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import com.example.instagramclonecoding.domain.user.entity.User;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record ArticleResponse(
        String id,
        User writer,
        String imageURL,
        String content,
        Date createdAt,
        List<LikeResponse> likeList,
        List<CommentResponse> commentList
) {
}
