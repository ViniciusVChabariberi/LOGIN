package com.vinivictor.api_login.repository.mongo;

import com.vinivictor.api_login.repository.orm.LoginOrmMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepositoryWithMongoDB extends MongoRepository<LoginOrmMongo, String> {
    Optional<LoginOrmMongo> findByUsername(String username);
}