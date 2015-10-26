package py.com.datapar.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.app.model.RespostaOpcao;

@RepositoryRestResource(collectionResourceRel = "respostaopcao", path = "respostaopcao")
public interface RespostaOpcaoRepository extends PagingAndSortingRepository<RespostaOpcao,Long> {

	List<RespostaOpcao> findByEnquetePerguntaId(long id);
	
	@Modifying
	@Query("update RespostaOpcao r set r.resposta = ?2 where r.enquetePergunta.id = ?1 and r.participante.id=1")
	int setResposta(long id, String resposta);
	
	
}





