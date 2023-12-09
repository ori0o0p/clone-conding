package com.example.instagramclonecoding.domain.user.controller;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.service.FindArticlesByUser;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/")
public class UserController {
    private final FindArticlesByUser findArticlesByUser;
    private final UserFacade userFacade;

    public UserController(FindArticlesByUser findArticlesByUser, UserFacade userFacade) {
        this.findArticlesByUser = findArticlesByUser;
        this.userFacade = userFacade;
    }

    @GetMapping("/articles/me")
    public Flux<ArticleResponse> getMyArticles() {
        User user = userFacade.getUser();
        return findArticlesByUser.execute(user.getId());
    }

    @GetMapping("/articles/{userId}")
    public Flux<ArticleResponse> getOtherUsersArticles(@PathVariable String userId) {
        return findArticlesByUser.execute(userId);
    }

}
