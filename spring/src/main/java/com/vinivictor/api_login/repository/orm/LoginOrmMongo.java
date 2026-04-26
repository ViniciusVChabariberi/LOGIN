package com.vinivictor.api_login.repository.orm;

import com.vinivictor.api_login.model.enumerable.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(value = "login")
public record LoginOrmMongo (
        @Id
        String id,
        String username,
        String password,
        Set<Role> roles
){
}