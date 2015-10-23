package py.com.datapar.enquete.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.Enquete;

@RepositoryRestResource(collectionResourceRel = "enquete", path = "enquete")
public interface EnqueteRepository extends PagingAndSortingRepository<Enquete,Long> {

	
}





