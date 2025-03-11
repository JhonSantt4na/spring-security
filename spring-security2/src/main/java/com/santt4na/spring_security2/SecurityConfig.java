package com.santt4na.spring_security2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/public").permitAll() // Rota publica free
        .anyRequest().authenticated()) // Qualquer outra requisicao precisa de auth
        // .formLogin(Customizer.withDefaults()) // adicionar o formulario de login
        .oauth2Login(Customizer.withDefaults()) // adicionamos o oauth do google para cookies
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .build();
  }

}