package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.comment.repository.CommentRepository;
import com.example.instagramclonecoding.domain.like.repository.LikeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class DeleteArticle {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public DeleteArticle(ArticleRepository articleRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    // req : articleId
    // 수행 : 게시물 삭제, 좋아요' 댓글 삭제(cascade)

    public Mono<Void> execute(String articleId) {

        return articleRepository.findById(articleId)
                .filter(Objects::nonNull)
                .flatMap(article -> articleRepository.delete(article)
                        .then(commentRepository.deleteAll(article.getCommentList()))
                        .then(likeRepository.deleteAll(article.getLikeList()))
                        .then()).then();
    }

}
