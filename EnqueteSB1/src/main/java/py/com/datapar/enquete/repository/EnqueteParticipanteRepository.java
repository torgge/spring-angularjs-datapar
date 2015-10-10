package py.com.datapar.enquete.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.EnqueteParticipante;

@RepositoryRestResource(collectionResourceRel = "enqueteparticipante", path = "enqueteparticipante", exported=false)
public interface EnqueteParticipanteRepository extends PagingAndSortingRepository<EnqueteParticipante,Long> {

	
}





