package py.com.datapar.enquete.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.RespostaOpcao;

@RepositoryRestResource(collectionResourceRel = "respostaopcao", path = "respostaopcao")
public interface RespostaOpcaoRepository extends PagingAndSortingRepository<RespostaOpcao,Long> {

	
}





