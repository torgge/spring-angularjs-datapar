package com.idomine.masterchief.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.idomine.masterchief.model.PedidoItem;

@RepositoryRestResource(collectionResourceRel = "pedidoitem", path = "pedidoitem")
public interface PedidoItemRepository extends PagingAndSortingRepository<PedidoItem,Long> {

	
}





