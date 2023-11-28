package com.example.instagramclonecoding.domain.user.dto;

import jakarta.validation.constraints.Email;

public record SignupRequest(@Email String email,
                            String name,
                            String password) {
}
