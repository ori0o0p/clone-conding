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

    public Mono<Void> execute(ArticleRequest request, MultipartFile image) {
        User user = userFacade.getUser();

        return Mono.just(
                Article.builder()
                        .imageURL("") // image
                        .content(request.content())
                        .writer(user)
                        .createdAt(new Date())
                        .build())
                .flatMap(articleRepository::save).then();
    }

}
