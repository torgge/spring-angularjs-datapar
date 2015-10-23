package MasterChicoSB1.model.wrapper;

import java.util.List;

import MasterChicoSB1.model.Pedido;
import MasterChicoSB1.model.PedidoItem;
/**
 * 
 * Usada para deserializar objetos complexos recebidos por parâmetros de requisões. 
 * 
 * @author Lyndon Tavares
 *
 */
public class PedidoWrapper {

	Pedido pedido;

	List<PedidoItem> listaItens;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<PedidoItem> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<PedidoItem> listaItens) {
		this.listaItens = listaItens;
	}
	
	
 	
}
