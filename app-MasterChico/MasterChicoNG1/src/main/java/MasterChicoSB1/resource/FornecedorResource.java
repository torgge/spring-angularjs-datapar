package MasterChicoSB1.resource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import MasterChicoSB1.model.Fornecedor;
import MasterChicoSB1.repository.FornecedorRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository repository;

	@Transactional
	@RequestMapping("/fornecedor/gerar/{numero}")
	public Iterable<Fornecedor> gerar100( @PathVariable long numero){
		if (numero==0){numero=100;};
		for ( int i=1;i<=100;i++){
			Fornecedor c = new Fornecedor(i,"Fornecedor-"+i,"9999-0000");
			repository.save(c);
		}
		return repository.findAll();
	}
	 
	@RequestMapping("/fornecedor")
	public Iterable<Fornecedor> listaFornecedores(){
		return repository.findAll() ;
	}
	
	@RequestMapping("/fornecedor/{id}")
	public Fornecedor findByNome(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping( value="/fornecedor", method = RequestMethod.POST)
	public Fornecedor addCategoria(@RequestBody final  Fornecedor fornecedor ){
		Fornecedor f = repository.save(fornecedor);
		return f;
	}

	@RequestMapping( value="/fornecedor", method = RequestMethod.PUT)
	public Fornecedor updateCategoria(@RequestBody final  Fornecedor fornecedor ){
		Fornecedor f = repository.save(fornecedor);
		return f;
	}

	@RequestMapping( value="/fornecedor/{id}", method = RequestMethod.DELETE)
	public void deleteFornecedor(@PathVariable final long id ){
		repository.delete(id);
	}
	
	
}
