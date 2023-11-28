package com.example.instagramclonecoding.domain.user.dto;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {
}
