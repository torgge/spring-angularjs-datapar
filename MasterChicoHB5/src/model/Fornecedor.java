package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Fornecedor
 * 
 * @author  Lyndodn Tavares
 * @version 1.0
 * 
 */
@Entity
@Table(name="tab_fornecedor")
public class Fornecedor {

	@Id
	@GeneratedValue
	private long id;
	
	private String nome;
	
	private String fone;
	
	public Fornecedor(){
	}
	

	public Fornecedor(long id, String nome, String fone) {
		super();
		this.id = id;
		this.nome = nome;
		this.fone = fone;
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
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", fone=" + fone + "]";
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
		Fornecedor other = (Fornecedor) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
