package com.vinivictor.api_login.repository.security;

import com.vinivictor.api_login.model.Login;
import com.vinivictor.api_login.repository.LoginRepository;
import com.vinivictor.api_login.repository.security.dto.AuthUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurity implements UserDetailsService {

    private final LoginRepository repository;

    public UserDetailsSecurity(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login user = repository.findByUsername(username);
        return new AuthUserDetails(user);
    }
}
