package com.example.instagramclonecoding.domain.comment.service;

import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.comment.repository.CommentRepository;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    public Mono<Void> execute() {
        // 유저 정보 가져오기
        // 게시물 검증
        // 댓글 추가
    }


}
