package MasterChicoSB1.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import MasterChicoSB1.model.Categoria;

@RepositoryRestResource(collectionResourceRel = "categoria", path = "categoria")
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria,Long> {

	List<Categoria> findByDescricaoAndId( @Param(value = "descricao") String descricao ,@Param(value = "id") long id);
	
}





