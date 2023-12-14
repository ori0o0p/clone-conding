package com.example.instagramclonecoding.domain.article.service.facade;

import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ArticleFacade {
    private final ArticleRepository articleRepository;

    public ArticleFacade(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Article> getArticle(String articleId) {
        return articleRepository.findById(articleId)
                .switchIfEmpty(Mono.error(() -> new RuntimeException("게시물을 찾지 못했습니다.")));
    }


}
