package py.com;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

	public List<Produto> findByNome(String nome);
	
	
}
