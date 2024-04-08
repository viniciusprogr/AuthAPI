package com.cc.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.login.dtos.inputs.AuthInput;
import com.cc.login.services.AuthService;

@RequestMapping("/auth")
@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping
	
	public String auth(@RequestBody AuthInput authInput) {
		var  tokenUsuario = new UsernamePasswordAuthenticationToken(authInput.getEmail(), authInput.getSenha());
		authenticationManager.authenticate(tokenUsuario);
		return authService.obterToken(authInput);
		
	}
	
}
