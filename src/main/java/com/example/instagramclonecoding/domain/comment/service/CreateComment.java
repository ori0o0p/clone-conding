package com.example.instagramclonecoding.domain.comment.service;

import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.comment.dto.CommentRequest;
import com.example.instagramclonecoding.domain.comment.entity.Comment;
import com.example.instagramclonecoding.domain.comment.repository.CommentRepository;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class CreateComment {
    private final CommentRepository commentRepository;
    private final UserFacade userFacade;
    private final ArticleRepository articleRepository;

    public CreateComment(CommentRepository commentRepository, UserFacade userFacade, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.userFacade = userFacade;
        this.articleRepository = articleRepository;
    }

    // req : 게시물 ID, 댓글 내용(content)
    public Mono<Void> execute(CommentRequest request) {
        // 유저 정보 가져오기
        User user = userFacade.getUser();
        // 게시물 검증
        return articleRepository.findById(request.articleId())
                .switchIfEmpty(Mono.error(() -> new RuntimeException("게시물을 찾지 못함")))
                .flatMap(article -> {
                    Comment comment = Comment.builder()
                            .content(request.content())
                            .createdAt(new Date())
                            .article(article)
                            .build();
                    // 댓글 추가 article save, comment save
                    article.addComment(comment);
                    return Mono.zip(commentRepository.save(comment),
                            articleRepository.save(article));
                })/*.onErrorResume(Mono::error)*/.then();
    }

}