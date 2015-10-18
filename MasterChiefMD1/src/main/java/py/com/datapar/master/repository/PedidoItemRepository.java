package py.com.datapar.master.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.master.model.PedidoItem;

@RepositoryRestResource(collectionResourceRel = "pedidoitem", path = "pedidoitem")
public interface PedidoItemRepository extends PagingAndSortingRepository<PedidoItem,Long> {

	
}





