package py.com.datapar.master.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.master.model.Unidade;

@RepositoryRestResource(collectionResourceRel = "unidade", path = "unidade", exported = true)
public interface UnidadeRepository extends PagingAndSortingRepository<Unidade,Long> {

	
}





