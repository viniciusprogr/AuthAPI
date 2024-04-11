package com.cc.login.configs;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cc.login.entities.UsuarioEntity;
import com.cc.login.repositories.UsuarioRepository;
import com.cc.login.services.AuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private AuthService authService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = capturaTokenHeader(request);

        if (token != null) {
            String email = authService.validaTokenJwt(token);
            UsuarioEntity usuario = usuarioRepository.findByEmail(email);
            
            var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autentication);
        }

        filterChain.doFilter(request, response);
    }

	
	public String capturaTokenHeader(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null) {
			return null;
		}
		if (!authHeader.split(" ")[0].equals("Bearer")) {
			return null;
		}
		return authHeader.split(" ")[1];
	}
}
