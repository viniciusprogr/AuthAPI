package com.cc.login.dtos.outputs;

import com.cc.login.enums.CargoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutput {
	
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private CargoEnum cargo;

}
