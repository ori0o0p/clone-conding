package com.example.instagramclonecoding.domain.comment.collection;

import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import com.example.instagramclonecoding.domain.comment.entity.Comment;

import java.util.List;
import java.util.Objects;

public class CommentCollection {
    private final List<Comment> commentList;

    public CommentCollection(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<CommentResponse> toCommentResponse() {
        return commentList.stream()
                .filter(Objects::nonNull)
                .map(CommentResponse::new)
                .toList();
    }

}
