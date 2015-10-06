package model;

import java.util.Date;
import java.util.List;
/**
 * Fornecedor
 * 
 * @author  Lyndodn Tavares
 * @version 1.0
 * 
 */
public class Pedido {

	private long id;
	private Date data;
	private Fornecedor fornecedor;
	private String situacao;
	
	private List<PedidoItem> pedidoItem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<PedidoItem> getPedidoItem() {
		return pedidoItem;
	}

	public void setPedidoItem(List<PedidoItem> pedidoItem) {
		this.pedidoItem = pedidoItem;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", fornecedor=" + fornecedor + ", situacao=" + situacao
				+ ", pedidoItem=" + pedidoItem + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
