package com.cc.login.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CargoEnum {
	 
	ADMIN("admin"),
	USER("user");
	
	private String cargo;

}
