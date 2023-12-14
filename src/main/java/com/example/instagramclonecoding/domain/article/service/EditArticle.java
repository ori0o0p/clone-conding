package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.dto.EditArticleRequest;
import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class EditArticle {
    private final ArticleRepository articleRepository;
    private final UserFacade userFacade;

    public EditArticle(ArticleRepository articleRepository, UserFacade userFacade) {
        this.articleRepository = articleRepository;
        this.userFacade = userFacade;
    }

    // req : 게시물 ID, 수정 사항 (content)

    public Mono<Void> execute(EditArticleRequest request) {
        User user = userFacade.getUser().block();
        return articleRepository.findById(request.id())
                .switchIfEmpty(Mono.error(() -> new RuntimeException("게시물을 찾지 못함")))
                .filter(article -> article.getWriter().equals(user))
                .flatMap(article -> {
                    // 수정 후 저장
                    article.editContent(request.content());
                    return articleRepository.save(article);
                }).then();
    }

}
