package py.com.datapar.app.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.com.datapar.app.model.Participante;
import py.com.datapar.app.model.TipoParticipante;
import py.com.datapar.app.repository.ParticipanteRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class ParticipanteResource {

	@Autowired
	private ParticipanteRepository repository;

	@RequestMapping("/participante")
	public Iterable<Participante> listaEnquetes(){
		return repository.findAll() ;
	}
	
	@RequestMapping("/participante/{nome}")
	public Participante findByNome(@PathVariable String nome){
		return repository.findByNomeIgnoreCase(nome);
	}
	
	@RequestMapping( value="/participante", method = RequestMethod.POST)
	public Participante addParticipante(@RequestBody final  Participante participante ){
		Participante p = repository.save(participante);	
		return p;
	}

	@RequestMapping( value="/participante", method = RequestMethod.PUT)
	public Participante updateParticipante(@RequestBody final  Participante participante ){
		Participante p = repository.save(participante);
		return p;
	}

	@RequestMapping( value="/participante/{id}", method = RequestMethod.DELETE)
	public void deleteParticipante(@PathVariable final long id ){
		repository.delete(id);
	}
	

	@RequestMapping("/participante/gerar/{numero}")
	public Iterable<Participante> gerar100( @PathVariable long numero){
		if (numero==0){numero=10;};
		for ( int i=1;i<=numero;i++){
			Participante p = new Participante( i, "Participante-"+i, "email_"+i+"@gmail.com" , true , TipoParticipante.ADMIN);
			repository.save(p);
		}
		return repository.findAll();
	}	
	
	
}
