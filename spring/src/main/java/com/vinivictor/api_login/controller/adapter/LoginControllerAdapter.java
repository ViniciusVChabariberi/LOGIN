package com.vinivictor.api_login.controller.adapter;

import com.vinivictor.api_login.controller.dto.request.LoginRequest;
import com.vinivictor.api_login.controller.dto.response.LoginResponse;
import com.vinivictor.api_login.model.Login;
import com.vinivictor.api_login.model.enumerable.Role;

import java.util.Set;

public class LoginControllerAdapter {
    private LoginControllerAdapter(){
    }

    public static Login castRequest(LoginRequest request){
        Set<Role> roles = request.roles() != null && !request.roles().isEmpty()
                ? request.roles()
                : Set.of(Role.USER);
        return new Login(
                null,
                request.username(),
                request.password(),
                roles
        );
    }

    public static Login castRequest(String id, LoginRequest request){
        Set<Role> roles = request.roles() != null && !request.roles().isEmpty()
                ? request.roles()
                : Set.of(Role.USER);
        return new Login(
                id,
                request.username(),
                request.password(),
                roles
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