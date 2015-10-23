package MasterChicoSB1.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import MasterChicoSB1.model.Pedido;

@RepositoryRestResource(collectionResourceRel = "pedido", path = "pedido")
public interface PedidoRepository extends PagingAndSortingRepository<Pedido,Long> {

	
}





