package com.example.instagramclonecoding.domain.like.repository;

import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.like.entity.Like;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {

    @Query(sort = "{user : -1}")
    Flux<Like> findByArticle(Article article);

}
