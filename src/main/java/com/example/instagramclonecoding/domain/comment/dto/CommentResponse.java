package com.example.instagramclonecoding.domain.comment.dto;

import com.example.instagramclonecoding.domain.comment.entity.Comment;
import lombok.Builder;

@Builder
public record CommentResponse(
        String id, // 댓글은 한 게시물에 중복 될 수 있으니 고유 아이디가 필요함
        String content
        // 댓글 좋아요 아직 구현 X
) {
    public CommentResponse(Comment comment) {
        this(comment.getId(), comment.getContent());
    }

}
