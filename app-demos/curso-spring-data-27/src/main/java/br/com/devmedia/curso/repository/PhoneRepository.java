package br.com.devmedia.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.curso.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
