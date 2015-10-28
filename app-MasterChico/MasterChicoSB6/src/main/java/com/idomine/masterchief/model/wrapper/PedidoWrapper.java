package com.idomine.masterchief.model.wrapper;

import java.util.List;

import com.idomine.masterchief.model.Pedido;
import com.idomine.masterchief.model.PedidoItem;
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
