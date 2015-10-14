package py.com.datapar.enquete.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.PerguntaOpcao;

@RepositoryRestResource(collectionResourceRel = "perguntaopcao", path = "perguntaopcao")
public interface PerguntaOpcaoRepository extends PagingAndSortingRepository<PerguntaOpcao,Long> {

	List<PerguntaOpcao> findByPerguntaId(long pergunta_id);
	
}





