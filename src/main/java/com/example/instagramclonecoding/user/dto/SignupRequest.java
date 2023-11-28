package com.example.instagramclonecoding.user.dto;

import jakarta.validation.constraints.Email;

public record SignupRequest(@Email String email,
                            String name,
                            String password) {
}
