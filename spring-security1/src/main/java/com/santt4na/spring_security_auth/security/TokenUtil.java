package com.santt4na.spring_security_auth.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.santt4na.spring_security_auth.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

  // Algumas constantes utilitaria
  private static final String EMISSOR = "santt4na";
  private static final String TOKEN_HEADER = "Bearer ";
  private static final String TOKEN_KEY = "01234567890123456789012345678901"; // chave de criptografia de 256 Bits
  private static final long UM_SEGUNDO = 1000;
  private static final long UM_MINUTO = 60 * UM_SEGUNDO;

  public static AuthToken encodedToken(Usuario user) {
    Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes()); // Transformando a o token_key em chave de criptografia
    String tokenJWT = Jwts.builder() // Construindo o Payload do token
        .setSubject(user.getLogin())
        .setIssuer(EMISSOR)
        .setExpiration(new Date(System.currentTimeMillis() + UM_MINUTO))
        .signWith(secretKey, SignatureAlgorithm.HS256) // Qual o algoritimo do secret
        .compact();

    AuthToken token = new AuthToken(TOKEN_HEADER + tokenJWT); // Criando o Retorno com o Bearer + Token

    return token;
  }

  public static Authentication decodeToken(HttpServletRequest request) throws Exception {
    try {
      String jwtToken = request.getHeader("Authorization"); // Pegando o token que vem no header
      jwtToken = jwtToken.replace(TOKEN_HEADER, ""); // Substituindo o Bearer por nada para removelo

      // Decodificando
      Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(TOKEN_KEY.getBytes()).build().parseClaimsJws(jwtToken);

      // Extraindo informacoes
      String usuario = jwsClaims.getBody().getSubject();
      String emissor = jwsClaims.getBody().getIssuer();
      Date validade = jwsClaims.getBody().getExpiration();

      // Validacao

      if (usuario.length() > 0 && emissor.equals(emissor) && validade.after(new Date(System.currentTimeMillis()))) {
        return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
      }

    } catch (Exception e) {
      System.out.println("Debug: Erro ao Decodificar Token");
      System.out.println(e.getMessage());
    }
    return null;
  }
}