package com.example.instagramclonecoding.domain.follow.service;

import com.example.instagramclonecoding.domain.follow.repository.FollowRepository;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.repository.UserRepository;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class FollowService {
    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public FollowService(UserFacade userFacade, UserRepository userRepository) {
        this.userFacade = userFacade;
        this.userRepository = userRepository;
    }

    public Mono<Void> execute(String otherUser) {
        return userRepository.findById(otherUser)
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(user -> {
                    User me = userFacade.getUser();
                    if (user.getFollowerList().contains(me)) {
                        user.getFollowerList().remove(me);
                        me.getFollowList().remove(user);
                    } else {
                        user.getFollowerList().add(me);
                        me.getFollowList().add(user);
                    }
                    return Mono.zip(userRepository.save(user), userRepository.save(me));
                }).then();
    }

}
