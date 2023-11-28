package com.example.instagramclonecoding.user.controller;

import com.example.instagramclonecoding.user.dto.LoginRequest;
import com.example.instagramclonecoding.user.dto.SignupRequest;
import com.example.instagramclonecoding.user.dto.TokenResponse;
import com.example.instagramclonecoding.user.service.Login;
import com.example.instagramclonecoding.user.service.Signup;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final Login login;
    private final Signup signup;

    public AuthController(Login login, Signup signup) {
        this.login = login;
        this.signup = signup;
    }

    @PostMapping("/login")
    public Mono<TokenResponse> setLogin(@RequestBody LoginRequest request) {
        return login.execute(request);
    }

    @PostMapping("/signup")
    public Mono<Void> setSignup(@RequestBody SignupRequest request) {
        return signup.execute(request);
    }

}
