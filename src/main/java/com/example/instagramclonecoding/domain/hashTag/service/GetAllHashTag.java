package com.example.instagramclonecoding.domain.hashTag.service;

import com.example.instagramclonecoding.domain.hashTag.dto.HashTagResponse;
import com.example.instagramclonecoding.domain.hashTag.repository.HashTagRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class GetAllHashTag {
    private final HashTagRepository hashTagRepository;

    public GetAllHashTag(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public Flux<HashTagResponse> execute() {
        return hashTagRepository.findAll()
                .collectList()
                .flatMap(hashTags -> Flux.fromIterable(hashTags)
                        .map(HashTagResponse::new)
                        .collectList())
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
