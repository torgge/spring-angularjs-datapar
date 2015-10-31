package com.idomine.masterchief.resource;

import java.math.BigDecimal;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.idomine.masterchief.model.Categoria;
import com.idomine.masterchief.model.Mercaderia;
import com.idomine.masterchief.model.Unidade;
import com.idomine.masterchief.model.views.MercadoriaView;
import com.idomine.masterchief.repository.MercaderiaRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class MercaderiaResource {

	@Autowired
	private MercaderiaRepository repository;

	@Transactional
	@RequestMapping("/mercaderia/gerar/{numero}")
	public Iterable<Mercaderia> gerar100( @PathVariable long numero){
		if (numero==0){numero=100;};
		for ( int i=1;i<=numero;i++){
			Mercaderia c = new Mercaderia(
					i,"Mercaderia-"+i,
					new BigDecimal( new Random().nextInt(1000) ),
					new BigDecimal(new Random().nextInt(1000)),
					new Unidade(new Random().nextInt(10)+1,""),
					new Categoria(new Random().nextInt(10)+1,""));
			repository.save(c);
		}
		return repository.findAll( );
	}

	@RequestMapping("/mercaderia")
	public Iterable<Mercaderia> listaMercaderiasGeral(){
		return repository.findAll(  ) ;
	}
	
	
	@JsonView(MercadoriaView.Detalhado.class)
	@RequestMapping("/mercaderia/detalhado")
	public Iterable<Mercaderia> listaMercaderiasDetalhado(){
		return repository.findAll(  ) ;
	}

	@JsonView(MercadoriaView.Resumo.class)
	@RequestMapping("/mercaderia/resumo")
	public Iterable<Mercaderia> listaMercaderiasResumo(){
		return repository.findAll(  ) ;
	}

	@JsonView(MercadoriaView.Resumo.class)
	@RequestMapping("/mercaderia/gerencial")
	public Iterable<Mercaderia> listaMercaderiasGerencial(){
		return repository.findAll(  ) ;
	}

	@RequestMapping("/mercaderia/zeradas")
	public Iterable<Mercaderia> listaMercaderiasComQuantidadeNulaVisQueryDSL(){
		return repository.findAll(  ) ;
	}
	
	
	@RequestMapping("/mercaderia/{id}")
	public Mercaderia findByNome(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping( value="/mercaderia", method = RequestMethod.POST)
	public Mercaderia addUnidade(@RequestBody final  Mercaderia mercaderia ){
		Mercaderia uni = repository.save(mercaderia);	
		return uni;
	}

	@RequestMapping( value="/mercaderia", method = RequestMethod.PUT)
	public Mercaderia updateCategoria(@RequestBody final  Mercaderia mercaderia ){
		Mercaderia m = repository.save(mercaderia);
		return m;
	}

	@RequestMapping( value="/mercaderia/{id}", method = RequestMethod.DELETE)
	public void deleteUnidade(@PathVariable final long id ){
		repository.delete(id);
	}
	
	
}
