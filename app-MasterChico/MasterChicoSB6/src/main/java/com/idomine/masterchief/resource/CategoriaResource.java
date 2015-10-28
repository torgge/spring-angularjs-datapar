package com.idomine.masterchief.resource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idomine.masterchief.model.Categoria;
import com.idomine.masterchief.repository.CategoriaRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;

	@Transactional
	@RequestMapping("/categoria/gerar/{numero}")
	public Iterable<Categoria> gerar100( @PathVariable long numero){
		if (numero==0){numero=100;};
		for ( int i=1;i<=100;i++){
			Categoria c = new Categoria(i,"Categoria-"+i);
			repository.save(c);
		}
		return repository.findAll();
	}
	 
	@RequestMapping("/categoria")
	public Iterable<Categoria> listaCategorias(){
		return repository.findAll() ;
	}
	
	@RequestMapping("/categoria/{id}")
	public Categoria findByNome(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping( value="/categoria", method = RequestMethod.POST)
	public Categoria addCategoria(@RequestBody final  Categoria categoria ){
		Categoria catgo = repository.save(categoria);
		return catgo;
	}

	@RequestMapping( value="/categoria", method = RequestMethod.PUT)
	public Categoria updateCategoria(@RequestBody final  Categoria categoria ){
		Categoria catgo = repository.save(categoria);
		return catgo;
	}

	@RequestMapping( value="/categoria/{id}", method = RequestMethod.DELETE)
	public void deleteCategoria(@PathVariable final long id ){
		repository.delete(id);
	}
	
	
}
