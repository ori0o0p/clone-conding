package com.example.instagramclonecoding.domain.comment.collection;

import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import com.example.instagramclonecoding.domain.comment.entity.Comment;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

public class CommentCollection {
    private final List<Comment> commentList;

    public CommentCollection(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Flux<CommentResponse> toCommentResponse() {
        return Flux.fromIterable(commentList)
                .filter(Objects::nonNull)
                .map(CommentResponse::new);
    }

}
