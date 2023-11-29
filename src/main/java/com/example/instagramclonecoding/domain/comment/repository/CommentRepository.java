package com.example.instagramclonecoding.domain.comment.repository;

import com.example.instagramclonecoding.domain.comment.entity.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
}
