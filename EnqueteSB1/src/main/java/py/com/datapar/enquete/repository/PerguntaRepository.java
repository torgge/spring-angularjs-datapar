package py.com.datapar.enquete.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.Pergunta;

@RepositoryRestResource(collectionResourceRel = "pergunta", path = "pergunta")
public interface PerguntaRepository extends PagingAndSortingRepository<Pergunta,Long> {

	
}





