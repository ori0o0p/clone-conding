package com.example.instagramclonecoding.domain.article.service.facade;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.comment.collection.CommentCollection;
import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import com.example.instagramclonecoding.domain.like.collection.LikeCollection;
import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public class ArticleToArticleResponse {

    public Mono<ArticleResponse> rapping(Article article) {
        LikeCollection likeCollection = new LikeCollection(article.getLikeList());
        CommentCollection commentCollection = new CommentCollection(article.getCommentList());

        Mono<List<LikeResponse>> likeListMono = likeCollection.toLikeResponse().collectList();
        Mono<List<CommentResponse>> commentListMono = commentCollection.toCommentResponse().collectList();

        return Mono.zip(likeListMono, commentListMono)
                .map(tuple -> ArticleResponse.builder()
                        .id(article.getId())
                        .writer(article.getWriter())
                        .imageURL(article.getImageURL())
                        .content(article.getContent())
                        .createdAt(article.getCreatedAt())
                        .likeList(tuple.getT1())
                        .commentList(tuple.getT2())
                        .build());
    }


}
