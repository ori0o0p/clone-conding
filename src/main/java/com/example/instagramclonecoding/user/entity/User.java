package com.example.instagramclonecoding.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Builder
@Document
public class User {

    @Id
    private final String id;

    @NonNull
    private final String email;

    @NonNull
    private final String name;

    @NonNull
    private final String password;

}
