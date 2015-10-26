package py.com.datapar.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.app.model.EnqueteParticipante;

@RepositoryRestResource(collectionResourceRel = "enqueteparticipante", path = "enqueteparticipante", exported=false)
public interface EnqueteParticipanteRepository extends PagingAndSortingRepository<EnqueteParticipante,Long> {

	
}





