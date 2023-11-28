package com.example.instagramclonecoding.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    private String id;

    @DBRef(lazy = true)
    private User user;

    @Builder.Default
    private Integer followerCNT = 0;

    @Builder.Default
    private Integer followCNT = 0;

    @Builder.Default
    private Integer articleCNT = 0;

    public void setFollowerCNT() {
        this.followerCNT += 1;
    }

    public void setFollowCNT() {
        this.followCNT += 1;
    }

    public void setArticleCNT() {
        this.articleCNT += 1;
    }

    public void minusFollowerCNT() {
        this.followerCNT -= 1;
    }

    public void minusFollowCNT() {
        this.followCNT -= 1;
    }

    public void minusArticleCNT() {
        this.articleCNT -= 1;
    }

}
