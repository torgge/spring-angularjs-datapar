package py.com.datapar.master.resource;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.com.datapar.master.model.Mercaderia;
import py.com.datapar.master.model.Pedido;
import py.com.datapar.master.model.PedidoItem;
import py.com.datapar.master.repository.PedidoItemRepository;

/**
 * 
 * 
 * @author lyndon-pc
 * @author http://www.infoq.com/br/articles/rest-introduction
 *
 */

@RestController
@RequestMapping("/api")
public class PedidoItemResource {

	@Autowired
	private PedidoItemRepository repository;

	@Transactional
	@RequestMapping("/pedidoitem/gerar/{numero}")
	public Iterable<PedidoItem> gerar100( @PathVariable long numero){
		if (numero==0){numero=100;};
		for ( int i=1;i<=100;i++){
			Pedido p = new Pedido();
			p.setId(1);
			Mercaderia m = new Mercaderia();
			m.setId(1);
			PedidoItem c = new PedidoItem(i,p,m,new BigDecimal(100),new BigDecimal(0));
			repository.save(c);
		}
		return repository.findAll();
	}	
	 
	@RequestMapping("/pedidoitem")
	public Iterable<PedidoItem> listaPedidos(){
		return repository.findAll() ;
	}
	
	@RequestMapping("/pedidoitem/{id}")
	public PedidoItem findByNome(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping( value="/pedidoitem", method = RequestMethod.POST)
	public PedidoItem addPedidoItem(@RequestBody final  PedidoItem pedido ){
		PedidoItem p = repository.save(pedido);
		return p;
	}

	@RequestMapping( value="/pedidoitem", method = RequestMethod.PUT)
	public PedidoItem updateCategoria(@RequestBody final  PedidoItem item ){
		PedidoItem i = repository.save(item);
		return i;
	}

	@RequestMapping( value="/pedidoitem/{id}", method = RequestMethod.DELETE)
	public void deletePedidoItem(@PathVariable final long id ){
		repository.delete(id);
	}
	
	
}
