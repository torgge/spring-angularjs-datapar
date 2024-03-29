package MasterChicoSB1.resource;

import java.math.BigDecimal;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import MasterChicoSB1.model.Categoria;
import MasterChicoSB1.model.Mercaderia;
import MasterChicoSB1.model.Unidade;
import MasterChicoSB1.repository.MercaderiaRepository;

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
		for ( int i=1;i<=100;i++){
			Mercaderia c = new Mercaderia(i,"Mercaderia-"+i,new BigDecimal( new Random().nextInt(1000) ),new BigDecimal(new Random().nextInt(1000)),new Unidade(1,""),new Categoria(1,""));
			repository.save(c);
		}
		return repository.findAll();
	}
	 
	@RequestMapping("/mercaderia")
	public Iterable<Mercaderia> listaUnidades(){
		return repository.findAll() ;
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
