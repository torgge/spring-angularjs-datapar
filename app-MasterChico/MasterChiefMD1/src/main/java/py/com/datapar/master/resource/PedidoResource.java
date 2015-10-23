package py.com.datapar.master.resource;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.com.datapar.master.model.Fornecedor;
import py.com.datapar.master.model.Pedido;
import py.com.datapar.master.repository.PedidoRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class PedidoResource {

	@Autowired
	private PedidoRepository repository;

	@Transactional
	@RequestMapping("/pedido/gerar/{numero}")
	public Iterable<Pedido> gerar100( @PathVariable long numero){
		if (numero==0){numero=100;};
		for ( int i=1;i<=100;i++){
			Pedido p = new Pedido();
			p.setData(new Date());
			p.setFornecedor(new Fornecedor(1,"",""));
			repository.save(p);
		}
		return repository.findAll();
	}
	 
	@RequestMapping("/pedido")
	public Iterable<Pedido> listaPedidos(){
		return repository.findAll() ;
	}
	
	@RequestMapping("/pedido/{id}")
	public Pedido findByNome(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping( value="/pedido", method = RequestMethod.POST)
	public Pedido addPedido(@RequestBody final  Pedido fornecedor ){
		Pedido p = repository.save(fornecedor);
		return p;
	}

	@RequestMapping( value="/pedido", method = RequestMethod.PUT)
	public Pedido updateCategoria(@RequestBody final  Pedido pedido ){
		Pedido p = repository.save(pedido);
		return p;
	}

	@RequestMapping( value="/pedido/{id}", method = RequestMethod.DELETE)
	public void deletePedido(@PathVariable final long id ){
		repository.delete(id);
	}
	
	
}
