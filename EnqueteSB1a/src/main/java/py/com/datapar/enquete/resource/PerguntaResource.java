package py.com.datapar.enquete.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.com.datapar.enquete.model.Pergunta;
import py.com.datapar.enquete.repository.PerguntaRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class PerguntaResource {

	@Autowired
	private PerguntaRepository repository;

	@RequestMapping("/pergunta")
	public Iterable<Pergunta> listaPergunta(){
		return repository.findAll() ;
	}
	
	@RequestMapping("/pergunta/{id}")
	public Pergunta findByNome(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping( value="/pergunta", method = RequestMethod.POST)
	public Pergunta addPergunta(@RequestBody final  Pergunta pergunta ){
		Pergunta p = repository.save(pergunta);	
		return p;
	}

	@RequestMapping( value="/pergunta", method = RequestMethod.PUT)
	public Pergunta updatePergunta(@RequestBody final  Pergunta pergunta ){
		Pergunta p = repository.save(pergunta);
		return p;
	}

	@RequestMapping( value="/pergunta/{id}", method = RequestMethod.DELETE)
	public void deletePergunta(@PathVariable final long id ){
		repository.delete(id);
	}
	

	@RequestMapping("/pergunta/gerar/{numero}")
	public Iterable<Pergunta> gerar100( @PathVariable long numero){
		if (numero==0){numero=10;};
		for ( int i=1;i<=numero;i++){
			Pergunta p = new Pergunta( i, "Pergunta-"+i, null );
			repository.save(p);
		}
		return repository.findAll();
	}	
	
	
}
