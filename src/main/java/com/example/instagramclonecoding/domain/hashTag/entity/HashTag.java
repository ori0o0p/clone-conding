package com.example.instagramclonecoding.domain.hashTag.entity;

import com.example.instagramclonecoding.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.collections.api.factory.Lists;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class HashTag {
    // Unique | start with # | 띄어쓰기 x
    @MongoId //  ObjectId 사용 x
    private String tag;

    @DBRef
    private List<Article> articleList = Lists.fixedSize.of();

    @Builder
    public HashTag(String tag) {
        this.tag = tag;
    }

}
