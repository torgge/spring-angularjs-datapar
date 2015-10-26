package py.com.datapar.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.app.model.Pergunta;

@RepositoryRestResource(collectionResourceRel = "pergunta", path = "pergunta")
public interface PerguntaRepository extends PagingAndSortingRepository<Pergunta,Long> {

	
}





