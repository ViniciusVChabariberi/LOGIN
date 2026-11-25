package com.vinijoao.login.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.vinijoao.login.model.Token;
import com.vinijoao.login.model.Usuario;
import com.vinijoao.login.port.TokenEmissor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Emite o JWT (HS256) com a mesma biblioteca (Nimbus) que o Gateway usa para validar.
 * Troca do jjwt: o jjwt carrega classes por reflexao, o que quebra no executavel nativo (GraalVM).
 */
@Component
public class JwtTokenEmissor implements TokenEmissor {

    private final JwtEncoder encoder;
    private final Duration expiracao;

    public JwtTokenEmissor(
            @Value("${JWT_SECRET}") String secret,
            @Value("${JWT_EXPIRACAO_MINUTOS:60}") long expiracaoMinutos
    ) {
        SecretKey chave = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        this.encoder = new NimbusJwtEncoder(new ImmutableSecret<>(chave));
        this.expiracao = Duration.ofMinutes(expiracaoMinutos);
    }

    @Override
    public Token emitir(Usuario usuario) {
        Instant agora = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(usuario.email())
                .issuedAt(agora)
                .expiresAt(agora.plus(expiracao))
                .claim("uid", usuario.id())
                .claim("nome", usuario.nome())
                .claim("roles", List.of("ROLE_" + usuario.role().name()))
                .build();
        JwsHeader cabecalho = JwsHeader.with(MacAlgorithm.HS256).build();
        String jwt = encoder.encode(JwtEncoderParameters.from(cabecalho, claims)).getTokenValue();
        return new Token(jwt);
    }
}
