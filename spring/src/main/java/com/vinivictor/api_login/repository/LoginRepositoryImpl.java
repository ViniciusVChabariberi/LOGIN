package com.vinivictor.api_login.repository;

import com.vinivictor.api_login.model.Login;
import com.vinivictor.api_login.repository.adapter.LoginRepositoryAdapter;
import com.vinivictor.api_login.repository.mongo.LoginRepositoryWithMongoDB;
import com.vinivictor.api_login.repository.orm.LoginOrmMongo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepositoryImpl implements LoginRepository{
    private final PasswordEncoder encoder;
    private final LoginRepositoryWithMongoDB repository;

    public LoginRepositoryImpl(PasswordEncoder encoder, LoginRepositoryWithMongoDB repository){
        this.encoder = encoder;
        this.repository = repository;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Login save(Login login) {
        LoginOrmMongo orm = LoginRepositoryAdapter.castEntity(login, encoder);
        LoginOrmMongo ormSave = repository.save(orm);
        return LoginRepositoryAdapter.castOrm(ormSave);
    }

    @Override
    public Login update(Login login) {
        LoginOrmMongo orm = LoginRepositoryAdapter.castEntity(login, encoder);
        LoginOrmMongo ormSave = repository.save(orm);
        return LoginRepositoryAdapter.castOrm(ormSave);
    }

    @Override
    public Login findById(String id) {
        return repository.findById(id)
                .map(LoginRepositoryAdapter::castOrm)
                .orElse(null);
    }

    @Override
    public Login findByUsername(String username) {
        try {
            Optional<LoginOrmMongo> optional = repository.findByUsername(username);
            if (optional.isEmpty()) {
                throw new UsernameNotFoundException("Usuario não encontrado");
            }
            return LoginRepositoryAdapter.castOrm(optional.get());
        } catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}