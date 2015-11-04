package com.idomine.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.idomine.model.Autor;

public interface AutorRepository extends PagingAndSortingRepository<Autor, Long> {

}
