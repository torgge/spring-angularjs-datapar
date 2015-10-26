package MasterChicoSB1.resource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import MasterChicoSB1.model.Categoria;
import MasterChicoSB1.repository.CategoriaRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
//@Api(basePath = "/api", value = "API", description = "Operations with Landlords", produces = "application/json")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;

	@Transactional
	@RequestMapping("/categoria/gerar/{numero}")
	@ApiOperation(value = "Gerar registros de categoria", notes = "Cria novos registros de categoria para testes")
    @ApiResponses(value = {
   	@ApiResponse(code = 500, message = "Erro no servidor"),
   	@ApiResponse(code = 200, message = "Registros gerados com sucesso") })

	public Iterable<Categoria> gerar100( @PathVariable long numero){
		if (numero==0){numero=100;};
		for ( int i=1;i<=100;i++){
			Categoria c = new Categoria(i,"Categoria-"+i);
			repository.save(c);
		}
		return repository.findAll();
	}
	
	
	
	@ApiOperation(value="GET Lista de categorias",notes="Usa o usuário remoto logado")
	@RequestMapping("/categoria")
	public Iterable<Categoria> listaCategorias(){
		return repository.findAll() ;
	}
	
	
	
	
	@ApiOperation(value="GET categoria por ID",notes="Usa o usuário remoto logado")
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
