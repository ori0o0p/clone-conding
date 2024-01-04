package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleFacade;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleToArticleResponse;
import com.example.instagramclonecoding.domain.comment.collection.CommentCollection;
import com.example.instagramclonecoding.domain.like.collection.LikeCollection;
import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

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
