package MasterChicoSB1.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import MasterChicoSB1.model.Mercaderia;

@RepositoryRestResource(collectionResourceRel = "mercaderia", path = "mercaderia")
public interface MercaderiaRepository extends PagingAndSortingRepository<Mercaderia,Long> {

	List<Mercaderia> findByQuantidade( @Param(value="quantidade") BigDecimal quantidade );
	
}





