package com.example.instagramclonecoding.domain.user.entity;

import com.example.instagramclonecoding.domain.article.entity.Article;
import com.example.instagramclonecoding.domain.follow.entity.Follow;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.eclipse.collections.api.factory.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String email;

    private String name;

    private String nickname;

    @JsonIgnore
    private String password;

    private String profileURL;

    @DBRef
    private Follow followInfo;

    @DBRef(lazy = true)
    private List<Article> articleList = Lists.fixedSize.of(); // 게시물 목록

    @DBRef(lazy = true)
    private UserInfo userInfo;// 게시물, 팔로잉, 팔로워 cnt

    private String info; // 소개 글

    @Builder
    public User(String email, String name, String nickname, String password) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
    }

    public void editInfo(String newInfo) {
        this.info = newInfo;
    }

    public void editProfile(String newProfile) { // 프로필 사진 변경
        this.profileURL = newProfile;
        // 만약 null 이면 default profile 필요(프로필 삭제 -> 기본 프로필 사용)
    }

    public void editNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

}
