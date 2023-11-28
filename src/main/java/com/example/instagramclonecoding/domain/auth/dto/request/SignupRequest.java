package com.example.instagramclonecoding.domain.auth.dto.request;

import jakarta.validation.constraints.Email;

public record SignupRequest(@Email String email,
                            String name,
                            String password) {
}
