package com.santt4na.spring_security2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringSecurity2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity2Application.class, args);
	}

	@RestController
	class HttpController {
		@GetMapping("/public")
		public String publicRoute() {
			return "<h1> Public route, fell free to look around! </h1>";
		}

		@GetMapping("/private")
		public String privateRoute() {
			return "<h1> Private route, only authorized personal !</h1>";
		}

		// Obtendo informacoes de quem esta logado
		@GetMapping("/cookie")
		public String cookie(@AuthenticationPrincipal OidcUser principal) {
			return String.format("""
					<h1> Oauth 2 </h1>"
					<br>
					<h3>Principal : %s</h3>
					<br>
					<h3>Email : %s</h3>
					<br>
					<h3>Authorities : %s</h3>
					<br>
					<h3>jwt : %s</h3>
					""", principal, principal.getAttribute("email"), principal.getAuthorities(),
					principal.getIdToken().getTokenValue());
		}

		@GetMapping("/jwt")
		public String jtw(@AuthenticationPrincipal Jwt jwt) {
			return String.format("""
					<h1> Jwt </h1>
					Principal : %s
					<br>
					Atributs : %s
					<br>
					Jwt : %s
					""", jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());
		}

	}
}
