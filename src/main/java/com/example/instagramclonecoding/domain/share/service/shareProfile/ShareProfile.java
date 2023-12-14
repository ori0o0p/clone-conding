package com.example.instagramclonecoding.domain.share.service.shareProfile;

import com.example.instagramclonecoding.domain.share.service.ShareService;
import com.example.instagramclonecoding.domain.user.entity.User;
import com.example.instagramclonecoding.domain.user.service.facade.UserFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
public class ShareProfile {
    private final ShareService shareService;
    private final UserFacade userFacade;

    public ShareProfile(ShareService shareService, UserFacade userFacade) {
        this.shareService = shareService;
        this.userFacade = userFacade;
    }

    public Mono<BufferedImage> execute() {
        return shareService.execute("user/" + userFacade.getUser()
                .map(User::getId));
    }

}
