package MasterChicoSB1.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import MasterChicoSB1.model.PedidoItem;

@RepositoryRestResource(collectionResourceRel = "pedidoitem", path = "pedidoitem")
public interface PedidoItemRepository extends PagingAndSortingRepository<PedidoItem,Long> {

	
}





