package com.idomine.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.idomine.model.Livro;

public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {

}
