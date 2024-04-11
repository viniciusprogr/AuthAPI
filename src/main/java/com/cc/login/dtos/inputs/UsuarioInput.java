package com.cc.login.dtos.inputs;

import com.cc.login.enums.CargoEnum;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioInput {

	private String nome;
	private String email;
	private String senha;
	private CargoEnum cargo;
}
