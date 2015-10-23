package py.com.datapar.master.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.master.model.Fornecedor;

@RepositoryRestResource(collectionResourceRel = "fornecedor", path = "fornecedor")
public interface FornecedorRepository extends PagingAndSortingRepository<Fornecedor,Long> {

	
}





