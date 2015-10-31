package com.idomine.masterchief.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.idomine.masterchief.model.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByNomeIgnoreCase(String nome);
	
}
