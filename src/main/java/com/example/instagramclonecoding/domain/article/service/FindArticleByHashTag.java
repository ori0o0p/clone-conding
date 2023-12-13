package com.example.instagramclonecoding.domain.article.service;

import com.example.instagramclonecoding.domain.article.dto.SearchArticleResponse;
import com.example.instagramclonecoding.domain.hashTag.entity.HashTag;
import com.example.instagramclonecoding.domain.hashTag.repository.HashTagRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class FindArticleByHashTag {
    private final HashTagRepository hashTagRepository;

    public FindArticleByHashTag(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public Flux<SearchArticleResponse> execute(String hashTag, String lastArticleId, int size) {
        return hashTagRepository.findById(hashTag)
                .map(HashTag::getArticleList)
                //.map(articles -> Flux.fromIterable(articles)) // Mono<Flux<Article>>
                .flatMap(articles -> Flux.fromIterable(articles) // Flux<Article>
                        .filter(article -> lastArticleId == null ||
                                new ObjectId(article.getId()).getTimestamp() > new ObjectId(lastArticleId).getTimestamp())
                        .take(size)
                        .map(SearchArticleResponse::new)
                        .collectList())
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
