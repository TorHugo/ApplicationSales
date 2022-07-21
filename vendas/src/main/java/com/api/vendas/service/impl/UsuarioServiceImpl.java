package com.api.vendas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService { // interface para carregar o login da base de dados.

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("user")){
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return User
                .builder()
                .username("user")
                .password(passwordEncoder.encode("123"))
                .roles("USER", "ADMIN")
                .build();
    }
}
