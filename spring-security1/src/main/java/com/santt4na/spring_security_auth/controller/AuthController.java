package com.santt4na.spring_security_auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.santt4na.spring_security_auth.model.Usuario;
import com.santt4na.spring_security_auth.security.AuthToken;
import com.santt4na.spring_security_auth.security.TokenUtil;

@RestController
public class AuthController {

  @GetMapping("/free")
  public String sayFreeHello() {
    return "Este eh um endpoint liberado pela nossa API";
  }

  @GetMapping("/auth")
  public String sayAuthHello() {
    return "Este e um andpoint que precisa de Autenticacao";
  }

  @PostMapping("/login")
  public ResponseEntity<AuthToken> realizarLogin(@RequestBody Usuario user) {
    if (user.getLogin().equals("santt4na") && user.getSenha().equals("1234")) {
      return ResponseEntity.ok(TokenUtil.encodedToken(user));
    }
    return ResponseEntity.status(403).build();
  }
}
