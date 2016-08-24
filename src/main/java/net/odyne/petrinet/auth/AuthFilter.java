package net.odyne.petrinet.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import net.odyne.petrinet.entities.AuthToken;
import net.odyne.petrinet.repositories.AuthTokenRepository;

@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private AuthTokenRepository authTokenRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		SecurityContext context = SecurityContextHolder.getContext();
		System.out.println("filter");
		if (!request.getServletPath().equals("/login")) {
			String xAuth = request.getHeader("authtoken");
			AuthToken token = authTokenRepository.findBySalt(xAuth);
			if (token != null && token.isExpired()) {
				authTokenRepository.delete(token);
				token = null;
			}
			if (token == null) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return;
			}
			context.setAuthentication(new UsernamePasswordAuthenticationToken(token.getUser(), token.getSalt()));
//			context.setAuthentication(new SpringToken(token));
		}
		chain.doFilter(request, response);
	}

}
