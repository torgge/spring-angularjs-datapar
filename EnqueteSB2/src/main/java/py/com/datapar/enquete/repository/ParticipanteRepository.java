package py.com.datapar.enquete.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.Participante;

@RepositoryRestResource(collectionResourceRel = "participante", path = "participante")
public interface ParticipanteRepository extends PagingAndSortingRepository<Participante,Long> {

	
	public Participante findByNomeIgnoreCase(String nome);
	
	
}





