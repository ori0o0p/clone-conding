package com.example.instagramclonecoding.domain.comment.entity;

import com.example.instagramclonecoding.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.collections.api.factory.Lists;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String id;

    @DBRef
    private Article article;

    private String content;

    private List<Comment> childCommentList = Lists.fixedSize.of();

    // 좋아요 수
    // 작성일
    @CreatedDate
    private Date createdAt;

    @Builder
    public Comment(String content, Date createdAt, Article article) {
        this.content = content;
        this.createdAt = createdAt;
        this.article = article;
    }

}
