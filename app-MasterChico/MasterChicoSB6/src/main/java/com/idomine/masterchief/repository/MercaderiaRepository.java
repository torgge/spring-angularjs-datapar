package com.idomine.masterchief.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.masterchief.model.Mercaderia;

@RepositoryRestResource(collectionResourceRel = "mercaderia", path = "mercaderia", exported = false)
public interface MercaderiaRepository extends 

 //crud repository com paginação
 PagingAndSortingRepository<Mercaderia,Long>, 
 
 //habilitar comportamento de predicados do querydsl
 JpaSpecificationExecutor<Mercaderia>  {

	List<Mercaderia> findByQuantidade( @Param(value="quantidade") BigDecimal quantidade );
	
}




