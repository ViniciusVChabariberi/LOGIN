package com.vinivictor.api_login.controller;

import com.vinivictor.api_login.controller.adapter.AuthControllerAdapter;
import com.vinivictor.api_login.controller.dto.request.LoginRequest;
import com.vinivictor.api_login.controller.dto.response.AuthResponse;
import com.vinivictor.api_login.model.Token;
import com.vinivictor.api_login.repository.security.TokenSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    private final TokenSecurity tokenSecurity;

    public AuthController(TokenSecurity tokenSecurity) {
        this.tokenSecurity = tokenSecurity;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public AuthResponse login(@RequestBody LoginRequest request) {
        Token token = tokenSecurity.gerarToken(AuthControllerAdapter.cast(request));
        return new AuthResponse(token.value());
    }

}
