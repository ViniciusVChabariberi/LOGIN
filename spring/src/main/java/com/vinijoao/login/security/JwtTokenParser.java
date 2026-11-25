package com.vinijoao.login.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenParser {

    private final JwtDecoder decoder;

    public JwtTokenParser(@Value("${JWT_SECRET}") String secret) {
        SecretKey chave = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        this.decoder = NimbusJwtDecoder.withSecretKey(chave).macAlgorithm(MacAlgorithm.HS256).build();
    }

    public String extrairUid(String token) {
        return decoder.decode(token).getClaimAsString("uid");
    }
}
