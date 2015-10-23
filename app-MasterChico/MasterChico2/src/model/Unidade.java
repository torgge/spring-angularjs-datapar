package model;

/**
 * Unidade
 * 
 * @author  Lyndodn Tavares
 * @version 1.0
 * 
 */
public class Unidade {

	private long id;
	
	private String descricao;
	
	public Unidade(){
	}
	
	public Unidade(long id,String descricao){
		this.id=id;
		this.descricao=descricao;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descricao=" + descricao + "]";
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
		Unidade other = (Unidade) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
