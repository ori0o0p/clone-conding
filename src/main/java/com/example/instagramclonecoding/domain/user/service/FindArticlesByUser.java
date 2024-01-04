package com.example.instagramclonecoding.domain.user.service;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleToArticleResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

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
