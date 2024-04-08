package com.cc.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cc.login.entities.UsuarioEntity;
import com.cc.login.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public UsuarioEntity cadastrarUsuario(UsuarioEntity usuarioEntity) {
		UsuarioEntity emailExistente = usuarioRepository.findByEmail(usuarioEntity.getEmail());
		
		if(emailExistente != null) {
			throw new RuntimeException("Email Ja Cadastrado");
		}
		
		String senhaCriptografada = passwordEncoder.encode(usuarioEntity.getSenha());
		
		usuarioEntity.setSenha(senhaCriptografada);
		
		return usuarioRepository.save(usuarioEntity);
	}

}
