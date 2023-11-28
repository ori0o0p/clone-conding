package com.example.instagramclonecoding.domain.auth.dto.request;

import jakarta.validation.constraints.Email;

public record LoginRequest(@Email String email, String password) {
}
