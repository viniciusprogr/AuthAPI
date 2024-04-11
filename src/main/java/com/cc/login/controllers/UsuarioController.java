package com.cc.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.login.converts.UsuarioConvert;
import com.cc.login.dtos.inputs.UsuarioInput;
import com.cc.login.dtos.outputs.UsuarioOutput;
import com.cc.login.entities.UsuarioEntity;
import com.cc.login.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioConvert usuarioConvert;

	@PostMapping("/cadastrar")
	public UsuarioOutput cadastrarUsuario(@RequestBody UsuarioInput usuarioInput) {
		UsuarioEntity usuarioConvertidoParaEntity = usuarioConvert.InputToNewEntity(usuarioInput);
		UsuarioEntity usuarioCadastrado = usuarioService.cadastrarUsuario(usuarioConvertidoParaEntity);
		return usuarioConvert.EntityToOutput(usuarioCadastrado);

	}
	
	@GetMapping("/admin")
	public String testeAdmin() {
		return new String("Você tem permissão ADMIN");
	}
	
	@GetMapping("/user")
	public String testeUser() {
		return new String("Você tem permissão USER");
	}
}
