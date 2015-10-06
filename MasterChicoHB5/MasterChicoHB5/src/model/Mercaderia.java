package model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Mercaderias
 * 
 * @author Lyndodn Tavares
 * @version 1.0
 * 
 */
@Entity
@Table(name = "tab_mercaderia")
@NamedQueries({ 
	@NamedQuery(name = "Mercaderia.findAll", query = "select m from Mercaderia m"),
	@NamedQuery(name = "Mercaderia.findByQuantidadeMaiorQue", query = "select m from Mercaderia m where m.quantidade > :quantidade") })
public class Mercaderia implements Comparable<Mercaderia> {

	@Id
	@GeneratedValue
	private long id;
	private String nome;
	private BigDecimal quantidade;
	private BigDecimal quantidadeMinima;

	@ManyToOne
	@JoinColumn(name = "unidade_id")
	private Unidade unidade;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Mercaderia() {

	}

	public Mercaderia(long id, String nome, BigDecimal quantidade, BigDecimal quantidadeMinima, Unidade unidade,
			Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.quantidadeMinima = quantidadeMinima;
		this.unidade = unidade;
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(BigDecimal quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Mercaderia [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", quantidadeMinima="
				+ quantidadeMinima + ", unidade=" + unidade + ", categoria=" + categoria + "]";
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
		Mercaderia other = (Mercaderia) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int compareTo(Mercaderia m) {
		if (this.quantidade.doubleValue() < m.quantidade.doubleValue()) {
			return -1;
		}
		if (this.quantidade.doubleValue() > m.quantidade.doubleValue()) {
			return 1;
		}
		return 0;
	}

}
