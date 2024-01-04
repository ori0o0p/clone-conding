package com.example.instagramclonecoding.domain.user.service;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleToArticleResponse;
import com.example.instagramclonecoding.domain.comment.collection.CommentCollection;
import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import com.example.instagramclonecoding.domain.like.collection.LikeCollection;
import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class FindArticlesByUser {
    private final ArticleRepository articleRepository;
    private final ArticleToArticleResponse articleToArticleResponse;

    public FindArticlesByUser(ArticleRepository articleRepository, ArticleToArticleResponse articleToArticleResponse) {
        this.articleRepository = articleRepository;
        this.articleToArticleResponse = articleToArticleResponse;
    }

    public Flux<ArticleResponse> execute(String userId, String lastArticleId, int size) {
        return articleRepository.findByUserId(userId)
                .filter(article -> lastArticleId == null ||
                        new ObjectId(article.getId()).getTimestamp() > new ObjectId(lastArticleId).getTimestamp())
                .take(size)
                .flatMap(articleToArticleResponse::rapping)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
