package com.idomine.appquizzer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.appquizzer.model.Pergunta;

@RepositoryRestResource(collectionResourceRel = "pergunta", path = "pergunta")
public interface PerguntaRepository extends PagingAndSortingRepository<Pergunta,Long> {

	
}





