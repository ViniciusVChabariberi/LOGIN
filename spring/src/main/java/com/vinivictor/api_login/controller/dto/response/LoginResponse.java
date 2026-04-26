package com.vinivictor.api_login.controller.dto.response;

import com.vinivictor.api_login.model.enumerable.Role;

import java.util.Set;

public record LoginResponse (
        String id,
        String username,
        Set<Role> roles
){
}
