package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleFacade;
import com.example.instagramclonecoding.domain.comment.collection.CommentCollection;
import com.example.instagramclonecoding.domain.like.collection.LikeCollection;
import com.example.instagramclonecoding.domain.like.dto.LikeResponse;
import com.example.instagramclonecoding.domain.comment.dto.CommentResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class FindArticleById {
    private final ArticleRepository articleRepository;
    private final ArticleFacade articleFacade;

    public FindArticleById(ArticleRepository articleRepository, ArticleFacade articleFacade) {
        this.articleRepository = articleRepository;
        this.articleFacade = articleFacade;
    }

    // req : article ID
    public Mono<ArticleResponse> execute(String articleId) {
        return articleFacade.getArticle(articleId)
                .map(this::rapping);
    }

    private ArticleResponse rapping(Article article) {
        LikeCollection likeCollection = new LikeCollection(article.getLikeList());
        CommentCollection commentCollection = new CommentCollection(article.getCommentList());

        Mono<List<LikeResponse>> likeListMono = likeCollection.toLikeResponse().collectList();
        Mono<List<CommentResponse>> commentListMono = likeCollection.toLikeResponse().collectList();
        
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
