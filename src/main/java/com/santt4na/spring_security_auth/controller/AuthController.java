package com.santt4na.spring_security_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
