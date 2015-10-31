package com.idomine.appquizzer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.appquizzer.model.Participante;

@RepositoryRestResource(collectionResourceRel = "participante", path = "participante")
public interface ParticipanteRepository extends PagingAndSortingRepository<Participante,Long> {

	
	public Participante findByNomeIgnoreCase(String nome);
	
	
}





