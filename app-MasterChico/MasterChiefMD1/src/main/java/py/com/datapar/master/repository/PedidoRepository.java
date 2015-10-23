package py.com.datapar.master.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.com.datapar.master.model.Pedido;

@RepositoryRestResource(collectionResourceRel = "pedido", path = "pedido")
public interface PedidoRepository extends PagingAndSortingRepository<Pedido,Long> {

	
	Iterable<Pedido> findByFornecedorNomeLike(@Param(value="fornecedor") String fornecedor);
	
}





