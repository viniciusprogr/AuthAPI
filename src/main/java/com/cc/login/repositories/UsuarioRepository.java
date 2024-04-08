package com.cc.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.login.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	UsuarioEntity findByEmail(String email);

	

}
