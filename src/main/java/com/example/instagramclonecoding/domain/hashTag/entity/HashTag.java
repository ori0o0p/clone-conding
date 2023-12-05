package com.example.instagramclonecoding.domain.hashTag.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashTag {
    // Unique | start with # | 띄어쓰기 x
    @MongoId //  ObjectId 사용 x
    private String tag;

}
