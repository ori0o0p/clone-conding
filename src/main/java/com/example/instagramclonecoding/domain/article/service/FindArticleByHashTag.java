package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.dto.SearchArticleResponse;
import com.example.instagramclonecoding.domain.hashTag.entity.HashTag;
import com.example.instagramclonecoding.domain.hashTag.repository.HashTagRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class FindArticleByHashTag {
    private final HashTagRepository hashTagRepository;

    public FindArticleByHashTag(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public Flux<SearchArticleResponse> execute(String hashTag) {
        Flux<SearchArticleResponse> list = hashTagRepository.findById(hashTag)
                .map(HashTag::getArticleList)
                .flatMap(articles -> Flux.fromIterable(articles)
                        .map(SearchArticleResponse::new)
                        .collectList())
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());

        return Flux.from(list);
    }

}
