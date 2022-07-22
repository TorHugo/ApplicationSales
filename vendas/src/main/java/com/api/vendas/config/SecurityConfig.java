package com.api.vendas.config;

import com.api.vendas.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // sempre gera um hash diferente, na senha do usuário.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // autenticacao do usuário
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());

    //user em memoria.
        //  .inMemoryAuthentication()
        //  .passwordEncoder(passwordEncoder())
        //  .withUser("user")
        //  .password(passwordEncoder().encode("123"))
        //  .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // autorizacao
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/clientes/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("api/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                .and()
                    .httpBasic();  // permite que faça uma requisição, passando no HEADER as credenciais.
    }
}
