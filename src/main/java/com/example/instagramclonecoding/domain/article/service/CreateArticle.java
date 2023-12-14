package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.dto.ArticleRequest;
import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.Date;


@Service
public class CreateArticle {
    private final ArticleRepository articleRepository;
    private final UserFacade userFacade;


    public CreateArticle(ArticleRepository articleRepository, UserFacade userFacade) {
        this.articleRepository = articleRepository;
        this.userFacade = userFacade;
    }

    // 해시 태그 추가 #
    // 위치 ?

    public Mono<Void> execute(ArticleRequest request, MultipartFile image) {
        return Mono.just(
                Article.builder()
                        .imageURL("") // image
                        .content(request.content())
                        .writer(userFacade.getUser()
                                .block())
                        .createdAt(new Date())
                        .build())
                .flatMap(articleRepository::save).then();
    }

}
