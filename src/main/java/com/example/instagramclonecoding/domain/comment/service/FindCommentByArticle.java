package com.example.instagramclonecoding.domain.comment.service;

import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;

@Service
public class FindCommentByArticle {
    private final ArticleRepository articleRepository;

    public FindCommentByArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // req : 게시물 id
    public Flux<CommentResponse> execute(String articleId) {
        return articleRepository.findById(articleId)
                .filter(Objects::nonNull)
                .map(Article::getCommentList)
                .flatMap(comments -> Flux.fromIterable(comments)
                        .map(CommentResponse::new)
                        .collectList())
                .flatMapMany(Flux::fromIterable) // Mono 객체를 Flux 객체로 변환
                .subscribeOn(Schedulers.boundedElastic());
    }

//    private Mono<CommentResponse> rapping(Comment comment) {
//        return Mono.just(CommentResponse.builder()
//                        .id(comment.getId())
//                        .content(comment.getId())
//                .build());
//    }

}
