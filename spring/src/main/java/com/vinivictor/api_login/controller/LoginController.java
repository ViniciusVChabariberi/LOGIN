package com.vinivictor.api_login.controller;

import com.vinivictor.api_login.controller.adapter.LoginControllerAdapter;
import com.vinivictor.api_login.controller.dto.request.LoginRequest;
import com.vinivictor.api_login.controller.dto.response.LoginResponse;
import com.vinivictor.api_login.model.Login;
import com.vinivictor.api_login.repository.LoginRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class LoginController {

    public final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    @PostMapping
    public LoginResponse salvar(@RequestBody LoginRequest request){

        Login login = LoginControllerAdapter.castRequest(request);

        Login loginSalvo = loginRepository.save(login);

        return LoginControllerAdapter.castResponse(loginSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoginResponse> atualizar(@PathVariable String id, @RequestBody LoginRequest request) {

        Login loginExistente = loginRepository.findById(id);
        if (loginExistente == null) {
            return ResponseEntity.notFound().build();
        }

        Login loginParaAtualizar = LoginControllerAdapter.castRequest(id, request);

        Login loginAtualizado = loginRepository.update(loginParaAtualizar);

        return ResponseEntity.ok(LoginControllerAdapter.castResponse(loginAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id){

        Login loginExistente = loginRepository.findById(id);

        if (loginExistente == null) {
            return ResponseEntity.notFound().build();
        }

        loginRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

}
