package com.example.instagramclonecoding.domain.share.service;

import com.example.instagramclonecoding.domain.share.qr.QrGenerateService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
public class ShareService {
    private final QrGenerateService qrGenerateService;

    public ShareService(QrGenerateService qrGenerateService) {
        this.qrGenerateService = qrGenerateService;
    }


    @Value("${default.url}")
    private String defaultURL;

    public Mono<BufferedImage> execute(String request) {
        String url = defaultURL + request;

        return Mono.just(qrGenerateService.execute(url));
    }


}
