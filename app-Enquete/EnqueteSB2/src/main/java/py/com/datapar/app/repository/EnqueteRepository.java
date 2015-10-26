package py.com.datapar.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.app.model.Enquete;

@RepositoryRestResource(collectionResourceRel = "enquete", path = "enquete")
public interface EnqueteRepository extends PagingAndSortingRepository<Enquete,Long> {

	//select * from tab_enquete where ativa=true
	Iterable<Enquete> findByAtivaTrue();
	
}





