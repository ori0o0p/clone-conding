package com.example.instagramclonecoding.domain.article.dto;

import com.example.instagramclonecoding.domain.comment.entity.Comment;
import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import com.example.instagramclonecoding.domain.like.entity.Like;
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
        List<Comment> commentList

) {

}
