package py.com.datapar.enquete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.enquete.model.EnquetePergunta;

@RepositoryRestResource(collectionResourceRel = "enquetepergunta", path = "enquetepergunta")
public interface EnquetePerguntaRepository extends PagingAndSortingRepository<EnquetePergunta,Long> {

	List<EnquetePergunta> findByEnqueteAtivaTrue();

	
}





