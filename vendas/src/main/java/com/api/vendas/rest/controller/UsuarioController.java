package com.api.vendas.rest.controller;

import com.api.vendas.domain.entity.Usuario;
import com.api.vendas.exception.SenhaInvalidaException;
import com.api.vendas.rest.dto.CredenciaisDTO;
import com.api.vendas.rest.dto.TokenDTO;
import com.api.vendas.security.jwt.JwtService;
import com.api.vendas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody @Valid Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try {
            Usuario usuario =  Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();
            String token = jwtService.gerarToken( usuario );

            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
