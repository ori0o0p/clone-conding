package com.example.instagramclonecoding.domain.like.service;

import com.example.instagramclonecoding.domain.article.repository.ArticleRepository;
import com.example.instagramclonecoding.domain.like.entity.Like;
import com.example.instagramclonecoding.domain.like.repository.LikeRepository;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class LikeToggleService {

    private final UserFacade userFacade;
    private final ArticleRepository articleRepository;
    private final LikeRepository likeRepository;

    public LikeToggleService(UserFacade userFacade, ArticleRepository articleRepository, LikeRepository likeRepositor) {
        this.userFacade = userFacade;
        this.articleRepository = articleRepository;
        this.likeRepository = likeRepositor;
    }

    // request: 게시물 id
    public Mono<Void> execute(String articleId) {
        User user = userFacade.getUser();
        return articleRepository.findById(articleId)
                .switchIfEmpty(Mono.error(new RuntimeException("게시물을 찾지 못함")))
                .flatMap(article -> {
                    Like newLike = Like.builder()
                            .user(user)
                            .article(article)
                            .build();

                    if (article.getLikeList().contains(newLike)) {
                        article.getLikeList().remove(newLike);
                        return likeRepository.delete(newLike)
                                .then(articleRepository.save(article))
                                .then();
                    } else {
                        article.getLikeList().add(newLike);
                        return likeRepository.save(newLike)
                                .then(articleRepository.save(article))
                                .then();
                    }
                });
    }

}
