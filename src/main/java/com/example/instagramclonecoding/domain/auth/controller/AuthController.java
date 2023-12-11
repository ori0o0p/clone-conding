package com.example.instagramclonecoding.domain.auth.controller;

import com.example.instagramclonecoding.domain.auth.dto.request.LoginRequest;
import com.example.instagramclonecoding.domain.auth.dto.response.TokenResponse;
import com.example.instagramclonecoding.domain.auth.service.Login;
import com.example.instagramclonecoding.domain.auth.dto.request.SignupRequest;
import com.example.instagramclonecoding.domain.auth.service.Signup;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> setSignup(@RequestBody SignupRequest request) {
        return signup.execute(request);
    }

}
