package com.idomine.masterchief.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.masterchief.model.Fornecedor;

@RepositoryRestResource(collectionResourceRel = "fornecedor", path = "fornecedor")
public interface FornecedorRepository extends PagingAndSortingRepository<Fornecedor,Long> {

	
}





