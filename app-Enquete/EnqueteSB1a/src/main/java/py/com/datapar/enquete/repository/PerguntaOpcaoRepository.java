package py.com.datapar.enquete.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.PerguntaOpcao;

@RepositoryRestResource(collectionResourceRel = "perguntaopcao", path = "perguntaopcao")
public interface PerguntaOpcaoRepository extends PagingAndSortingRepository<PerguntaOpcao,Long> {

	
}





