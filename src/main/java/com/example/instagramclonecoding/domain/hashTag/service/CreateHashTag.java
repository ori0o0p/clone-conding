package com.example.instagramclonecoding.domain.hashTag.service;

import com.example.instagramclonecoding.domain.hashTag.entity.HashTag;
import com.example.instagramclonecoding.domain.hashTag.repository.HashTagRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateHashTag {
    private final HashTagRepository hashTagRepository;

    public CreateHashTag(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    // tag is nonNUll & Unique
    // req : tag ' String
    // 해시태그를 만든 후 게시물에 저장

    public Mono<Void> execute(String tag) {
        return hashTagRepository.findById(tag)
                .flatMap(hashTag -> Mono.error(new RuntimeException("이미 사용할 수 있는 태그입니다."))) // 태그를 만들 수 없음
                .switchIfEmpty(Mono.defer(() ->{ // 태그를 만들 수 있음
                    HashTag hashTag = HashTag.builder()
                            .tag(tag)
                            .build();
                    return hashTagRepository.save(hashTag);
                })).then();
    }

}
