package com.cc.login.converts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cc.login.dtos.inputs.UsuarioInput;
import com.cc.login.dtos.outputs.UsuarioOutput;
import com.cc.login.entities.UsuarioEntity;

@Component
public class UsuarioConvert {
	
	@Autowired
	private ModelMapper modelMapper;

	public UsuarioEntity InputToNewEntity(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, UsuarioEntity.class);
	}

	public UsuarioOutput EntityToOutput(UsuarioEntity usuarioEntity) {
		return modelMapper.map(usuarioEntity, UsuarioOutput.class);
	}
	
}
