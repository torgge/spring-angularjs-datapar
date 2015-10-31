package com.idomine.appquizzer.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.appquizzer.model.PerguntaOpcao;

@RepositoryRestResource(collectionResourceRel = "perguntaopcao", path = "perguntaopcao")
public interface PerguntaOpcaoRepository extends PagingAndSortingRepository<PerguntaOpcao,Long> {

	List<PerguntaOpcao> findByPerguntaId(long pergunta_id);
	
}





