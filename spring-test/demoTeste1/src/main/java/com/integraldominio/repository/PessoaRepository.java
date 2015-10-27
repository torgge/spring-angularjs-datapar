package com.integraldominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integraldominio.modelo.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}