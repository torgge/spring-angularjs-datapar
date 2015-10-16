package com.example;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "produto", path = "produto")
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

}
