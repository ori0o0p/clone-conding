package com.example.instagramclonecoding.domain.article.entity;

import com.example.instagramclonecoding.domain.comment.entity.Comment;
import com.example.instagramclonecoding.domain.like.entity.Like;
import com.example.instagramclonecoding.domain.user.entity.User;
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
public class Article {
    @Id
    private String id;

    // 작성자
    @DBRef
    private User writer;

    // 게시물 사진
    private String imageURL;
    // 게시물 내용
    private String content;

    @CreatedDate
    private Date createdAt; // 게시물 작성일

    // 좋아요 수 | 좋아요 누른 사람
    @DBRef(lazy = true)
    private List<Like> likeList = Lists.fixedSize.of();

    // 댓글
    @DBRef(lazy = true)
    private List<Comment> commentList = Lists.fixedSize.of();

    // 좋아요 가리기, 댓글 기능 헤제
    @Builder
    public Article(String imageURL, String content, User writer, Date createdAt) {
        this.imageURL = imageURL;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    // 게시물 수정은 내용 수정만
    public void editContent(String newContent) {
        this.content = newContent;
    }

}
