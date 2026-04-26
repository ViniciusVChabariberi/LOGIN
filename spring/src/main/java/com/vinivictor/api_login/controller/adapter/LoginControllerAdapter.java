package com.vinivictor.api_login.controller.adapter;

import com.vinivictor.api_login.controller.dto.request.LoginRequest;
import com.vinivictor.api_login.controller.dto.response.LoginResponse;
import com.vinivictor.api_login.model.Login;

public class LoginControllerAdapter {
    private LoginControllerAdapter(){
    }

    public static Login castRequest(LoginRequest request){
        return new Login(
                null,
                request.username(),
                request.password(),
                request.roles()
        );
    }

    public static Login castRequest(String id, LoginRequest request){
        return new Login(
                id,
                request.username(),
                request.password(),
                request.roles()
        );
    }

    public static LoginResponse castResponse(Login login) {
        return new LoginResponse(
                login.id(),
                login.username(),
                login.roles()
        );
    }
}