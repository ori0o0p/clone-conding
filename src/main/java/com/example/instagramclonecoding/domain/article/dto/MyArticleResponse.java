package com.example.instagramclonecoding.domain.article.dto;

import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record MyArticleResponse(
        String id,
        String imageURL,
        String content,
        Date createdAt,
        List<LikeResponse> likeList,
        List<CommentResponse> commentList

) {

}
