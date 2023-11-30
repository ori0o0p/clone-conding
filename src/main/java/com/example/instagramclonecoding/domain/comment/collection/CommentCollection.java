package com.example.instagramclonecoding.domain.comment.collection;

import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import com.example.instagramclonecoding.domain.comment.entity.Comment;

import java.util.List;

public class CommentCollection {
    private final List<Comment> commentList;

    public CommentCollection(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<CommentResponse> toCommentResponse() {
        return commentList.stream()
                .map(CommentResponse::new)
                .toList();
    }

}
