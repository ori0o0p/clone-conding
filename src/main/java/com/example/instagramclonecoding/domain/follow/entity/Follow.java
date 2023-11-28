package com.example.instagramclonecoding.domain.follow.entity;

import com.example.instagramclonecoding.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.collections.api.factory.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    private String id;

    @DBRef
    private User user;

    private List<User> followerList = Lists.fixedSize.of(); // 팔로워 목록

    private List<User> followList = Lists.fixedSize.of(); // 팔로잉 목록

    public void follow(User user) { // 내가 다른 사람을 팔로우
        this.followList.add(user);
    }

    public void unFollow(User user) {
        this.followList.remove(user);
    }

    public void addFollower(User user) { // 다른 사람이 나를 팔로우
        this.followerList.add(user);
    }

    public void removeFollower(User user) {
        this.followerList.remove(user);
    }

}
