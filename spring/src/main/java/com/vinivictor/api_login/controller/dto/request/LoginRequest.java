package com.vinivictor.api_login.controller.dto.request;

import com.vinivictor.api_login.model.enumerable.Role;

import java.util.Set;

public record LoginRequest (
        String username,
        String password,
        Set<Role> roles
){
}
