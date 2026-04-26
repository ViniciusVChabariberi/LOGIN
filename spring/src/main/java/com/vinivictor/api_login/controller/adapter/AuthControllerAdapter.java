package com.vinivictor.api_login.controller.adapter;

import com.vinivictor.api_login.controller.dto.request.LoginRequest;
import com.vinivictor.api_login.model.Login;

public class AuthControllerAdapter {
    private AuthControllerAdapter() {
    }

    public static Login cast(LoginRequest request) {
        return new Login(
                null,
                request.username(),
                request.password(),
                null
        );
    }
}
