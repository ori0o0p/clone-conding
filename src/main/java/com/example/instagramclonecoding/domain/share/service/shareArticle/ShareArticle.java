package com.example.instagramclonecoding.domain.share.service.shareArticle;

import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.article.service.facade.ArticleFacade;
import com.example.instagramclonecoding.domain.share.service.ShareService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
public class ShareArticle {
    private final ShareService shareService;
    private final ArticleFacade articleFacade;

    public ShareArticle(ShareService shareService, ArticleFacade articleFacade) {
        this.shareService = shareService;
        this.articleFacade = articleFacade;
    }

    // req : article ID ,path : /articles/{id}
    public Mono<BufferedImage> execute(String articleId) {
        return shareService.execute("articles/" +
                getArticle(articleId).map(Article::getId));
    }

    private Mono<Article> getArticle(String articleId) {
        return articleFacade.getArticle(articleId);
    }

}
