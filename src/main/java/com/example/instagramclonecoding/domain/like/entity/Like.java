package com.example.instagramclonecoding.domain.like.entity;

import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Article article;

    @Builder
    public Like(User user, Article article) {
        this.user = user;
        this.article = article;
    }

}
