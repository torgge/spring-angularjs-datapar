package br.com.devmedia.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.curso.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
