package MasterChicoSB1.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import MasterChicoSB1.model.Fornecedor;

@RepositoryRestResource(collectionResourceRel = "fornecedor", path = "fornecedor")
public interface FornecedorRepository extends PagingAndSortingRepository<Fornecedor,Long> {

	
}





