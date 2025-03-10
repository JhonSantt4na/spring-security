package com.santt4na.spring_security_auth.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      try {
        Authentication auth = TokenUtil.decodeToken(request);
        if (auth != null) {
          SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
          ErroDTO erro = new ErroDTO(401, "Usuario Nao autorizado");
          response.setStatus(erro.getStatus());
          response.setContentType("Application/json");
          ObjectMapper mapper = new ObjectMapper();
          response.getWriter().print(mapper.writeValueAsString(erro));
          response.getWriter().flush();
          return;
        }
      } catch (Exception e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        return;
      }
    }

    filterChain.doFilter(request, response);
  }
}
