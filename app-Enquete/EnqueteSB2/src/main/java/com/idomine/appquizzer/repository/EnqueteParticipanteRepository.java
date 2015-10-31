package com.idomine.appquizzer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.appquizzer.model.EnqueteParticipante;

@RepositoryRestResource(collectionResourceRel = "enqueteparticipante", path = "enqueteparticipante", exported=false)
public interface EnqueteParticipanteRepository extends PagingAndSortingRepository<EnqueteParticipante,Long> {

	
}





