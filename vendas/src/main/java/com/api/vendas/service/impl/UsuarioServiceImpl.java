package com.api.vendas.service.impl;

import com.api.vendas.domain.entity.Usuario;
import com.api.vendas.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService { // interface para carregar o login da base de dados.

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(Usuario usuario){
        String senhaCripttografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCripttografada);

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findByLogin(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("Uusário não encontrado na base de dados.")
                );

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} :
                new String[]{"User"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
