package com.santt4na.spring_security_auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable()) // Desativando CSRF
        .cors(Customizer.withDefaults()) // Aplicando CORS
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/free").permitAll() // Permite acesso livre
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated() // Qualquer outra requisição precisa estar autenticada
        )
        .addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class) // Adiciona filtro customizado
        .build();
  }
}
