package py.com.datapar.master.resource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.com.datapar.master.model.Unidade;
import py.com.datapar.master.repository.UnidadeRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class UnidadeResource {

	@Autowired
	private UnidadeRepository repository;

	@Transactional
	@RequestMapping("/unidade/gerar/{numero}")
	public Iterable<Unidade> gerar100( @PathVariable long numero){
		if (numero==0){numero=100;};
		for ( int i=1;i<=100;i++){
			Unidade c = new Unidade(i,"Unidade-"+i);
			repository.save(c);
		}
		return repository.findAll();
	}
	 
	@RequestMapping("/unidade")
	public Iterable<Unidade> listaUnidades(){
		return repository.findAll() ;
	}
	
	@RequestMapping("/unidade/{id}")
	public Unidade findByNome(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping( value="/unidade", method = RequestMethod.POST)
	public Unidade addUnidade(@RequestBody final  Unidade unidade ){
		Unidade uni = repository.save(unidade);	
		return uni;
	}

	@RequestMapping( value="/unidade", method = RequestMethod.PUT)
	public Unidade updateCategoria(@RequestBody final  Unidade unidade ){
		Unidade uni = repository.save(unidade);
		return uni;
	}

	@RequestMapping( value="/unidade/{id}", method = RequestMethod.DELETE)
	public void deleteUnidade(@PathVariable final long id ){
		repository.delete(id);
	}
	
	
}
