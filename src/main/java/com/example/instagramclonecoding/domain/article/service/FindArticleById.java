package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleFacade;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleToArticleResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindArticleById {
    private final ArticleToArticleResponse articleToArticleResponse;
    private final ArticleFacade articleFacade;

    public FindArticleById(ArticleToArticleResponse articleToArticleResponse, ArticleFacade articleFacade) {
        this.articleToArticleResponse = articleToArticleResponse;
        this.articleFacade = articleFacade;
    }

    // req : article ID
    public Mono<ArticleResponse> execute(String articleId) {
        return articleFacade.getArticle(articleId)
                .flatMap(articleToArticleResponse::rapping);
    }

}
