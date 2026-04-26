package com.vinivictor.api_login.repository.adapter;

import com.vinivictor.api_login.model.Login;
import com.vinivictor.api_login.repository.orm.LoginOrmMongo;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginRepositoryAdapter {
    private LoginRepositoryAdapter() {}

    public static Login castOrm(LoginOrmMongo orm) {
        return new Login(
                orm.id(),
                orm.username(),
                orm.password(),
                orm.roles()
        );
    }

    public static LoginOrmMongo castEntity(Login entity, PasswordEncoder encoder) {
        return new LoginOrmMongo(
                entity.id(),
                entity.username(),
                encoder.encode(entity.password()),
                entity.roles()
        );
    }
}