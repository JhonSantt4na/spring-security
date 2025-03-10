package com.santt4na.spring_security_auth.security;

import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {
  public static Authentication decodeToken(HttpServletRequest request) throws Exception {
    if (request.getHeader("Authorization").equals("Bearer JhonnSantt4na")) {
      return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
    }
    return null;
  }
}
