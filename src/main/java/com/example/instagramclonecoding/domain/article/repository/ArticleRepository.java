package com.example.instagramclonecoding.domain.article.repository;

import com.example.instagramclonecoding.domain.article.entity.Article;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {
    @Query(sort = "{createdAt: -1}")
    Flux<Article> findByUserId(String id);
}
