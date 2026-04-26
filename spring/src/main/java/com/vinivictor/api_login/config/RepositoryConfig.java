package com.vinivictor.api_login.config;

import com.vinivictor.api_login.repository.LoginRepository;
import com.vinivictor.api_login.repository.LoginRepositoryImpl;
import com.vinivictor.api_login.repository.mongo.LoginRepositoryWithMongoDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RepositoryConfig {

    @Bean
    @Primary
    public LoginRepository loginRepository(LoginRepositoryWithMongoDB repository, PasswordEncoder encoder){
        return new LoginRepositoryImpl(encoder, repository);
    }
}
