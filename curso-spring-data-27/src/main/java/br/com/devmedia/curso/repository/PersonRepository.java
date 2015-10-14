package br.com.devmedia.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.curso.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	// busca por firstName igual a parametro
	List<Person> findByFirstNameLike(String firstName);
	
	// busca por firstName diferente do parametro
	List<Person> findByFirstNameNotLike(String firstName);
	
	// busca por age igual ao parametro fornecido
	List<Person> findByAge(Integer age);
	
	// busca por age diferente do parametro fornecido
	List<Person> findByAgeNot(Integer age);
}
