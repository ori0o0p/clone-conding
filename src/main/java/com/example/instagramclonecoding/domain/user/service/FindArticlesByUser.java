package com.example.instagramclonecoding.domain.user.service;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.comment.collection.CommentCollection;
import com.example.instagramclonecoding.domain.like.collection.LikeCollection;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class FindArticlesByUser {
    private final ArticleRepository articleRepository;
    private final UserFacade userFacade;

    public FindArticlesByUser(ArticleRepository articleRepository, UserFacade userFacade) {
        this.articleRepository = articleRepository;
        this.userFacade = userFacade;
    }

    public Flux<ArticleResponse> execute(String userId) {
        //User user = userFacade.getUser();

        Flux<ArticleResponse> list = articleRepository.findByUserId(userId)
                .flatMap(this::rapping)
                .subscribeOn(Schedulers.boundedElastic());

        return Flux.from(list);
    }

    private Mono<ArticleResponse> rapping(Article article) {
        LikeCollection likeCollection = new LikeCollection(article.getLikeList());
        CommentCollection commentCollection = new CommentCollection(article.getCommentList());

        return Mono.just(ArticleResponse.builder()
                        .id(article.getId())
                        .writer(article.getWriter())
                        .imageURL(article.getImageURL())
                        .content(article.getContent())
                        .createdAt(article.getCreatedAt())
                        .likeList(likeCollection.toLikeResponse())
                        .commentList(commentCollection.toCommentResponse())
                .build());
    }

}
