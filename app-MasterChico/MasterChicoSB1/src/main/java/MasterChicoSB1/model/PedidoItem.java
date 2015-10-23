package MasterChicoSB1.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Itens do pedido
 * 
 * @author  Lyndodn Tavares
 * @version 1.0
 * 
 */
@Entity
@Table(name="tab_pedido_item")
public class PedidoItem {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="mercaderia_id")
	private Mercaderia mercaderia;
	
	private BigDecimal quantidadePedido;
	
	private BigDecimal quantidadeRecebido;

	public PedidoItem(){
		
	}
	
	public PedidoItem(long id, Pedido pedido, Mercaderia mercaderia, BigDecimal quantidadePedido,
			BigDecimal quantidadeRecebido) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.mercaderia = mercaderia;
		this.quantidadePedido = quantidadePedido;
		this.quantidadeRecebido = quantidadeRecebido;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Mercaderia getMercaderia() {
		return mercaderia;
	}

	public void setMercaderia(Mercaderia mercaderia) {
		this.mercaderia = mercaderia;
	}

	public BigDecimal getQuantidadePedido() {
		return quantidadePedido;
	}

	public void setQuantidadePedido(BigDecimal quantidadePedido) {
		this.quantidadePedido = quantidadePedido;
	}

	public BigDecimal getQuantidadeRecebido() {
		return quantidadeRecebido;
	}

	public void setQuantidadeRecebido(BigDecimal quantidadeRecebido) {
		this.quantidadeRecebido = quantidadeRecebido;
	}

	@Override
	public String toString() {
		return "PedidoItem [id=" + id + ", pedido=" + pedido + ", mercaderia=" + mercaderia + ", quantidadePedido="
				+ quantidadePedido + ", quantidadeRecebido=" + quantidadeRecebido + "] \n";
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
		PedidoItem other = (PedidoItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
