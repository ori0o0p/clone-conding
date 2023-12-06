package com.example.instagramclonecoding.domain.article.dto;

import com.example.instagramclonecoding.domain.article.entity.Article;
import lombok.Builder;

import java.util.List;

@Builder
public record SearchArticleResponse(
        String id,
        String imageURL
) {

    public SearchArticleResponse(Article article) {
        this(article.getId(), article.getImageURL());
    }

}
