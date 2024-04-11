package com.cc.login.services;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cc.login.dtos.inputs.AuthInput;
import com.cc.login.entities.UsuarioEntity;
import com.cc.login.repositories.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(email);
	}
	
	public String obterToken(AuthInput authInput) {
		UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(authInput.getEmail());
		return gerarTokenJwt(usuarioEntity);
	}
	
	public String gerarTokenJwt(UsuarioEntity usuarioEntity) {
		try {
			
			Algorithm algorithm = Algorithm.HMAC256("segredo_token2a8j2jd00a-2kd0-22");
			
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(usuarioEntity.getEmail())
					.withExpiresAt(gerarDataExpiracao())
					.sign(algorithm);
			
			return token;
			
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao tentar gerar o token! " + exception.getMessage());
		}
		
	}

	
	public String validaTokenJwt(String token) {
		
		
		try {
			Algorithm algorithm = Algorithm.HMAC256("segredo_token2a8j2jd00a-2kd0-22");
			
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException jwtVerificationException) {
			return jwtVerificationException.getMessage();
		}
		
	}

	private Instant gerarDataExpiracao() {
		return LocalDateTime.now()
				.plusHours(8)
				.toInstant(ZoneOffset.of("-03:00"));
	}
	
	
	

}
