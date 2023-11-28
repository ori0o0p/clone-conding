package com.example.instagramclonecoding.domain.auth.dto;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {
}
