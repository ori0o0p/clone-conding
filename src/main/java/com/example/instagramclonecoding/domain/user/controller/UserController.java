package com.example.instagramclonecoding.domain.user.controller;

import com.example.instagramclonecoding.domain.article.dto.ArticleResponse;
import com.example.instagramclonecoding.domain.user.dto.LastArticleIdRequest;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.service.FindArticlesByUser;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.web.bind.annotation.*;
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
    public Flux<ArticleResponse> getMyArticles(@RequestBody LastArticleIdRequest request) {
        User user = userFacade.getUser();
        return findArticlesByUser.execute(user.getId(), request.lastArticleId(), 18);
    }

    @GetMapping("/articles/{userId}")
    public Flux<ArticleResponse> getOtherUsersArticles(@PathVariable String userId,
                                                       @RequestBody LastArticleIdRequest request) {
        return findArticlesByUser.execute(userId, request.lastArticleId(), 18);
    }

}
