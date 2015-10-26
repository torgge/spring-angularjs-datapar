package py.com.datapar.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tab_pergunta")
public class Pergunta {

	@Id
	@GeneratedValue
	private long id;
	
	private String descricao;
	
	@Enumerated(value=EnumType.STRING)
	private TipoPergunta tipoPergunta;

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

	
	public TipoPergunta getTipoPergunta() {
		return tipoPergunta;
	}

	public void setTipoPergunta(TipoPergunta tipoPergunta) {
		this.tipoPergunta = tipoPergunta;
	}

	
	public Pergunta(){
		
	}
	

	
	public Pergunta(long id, String descricao, TipoPergunta tipoPergunta) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipoPergunta = tipoPergunta;
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
		Pergunta other = (Pergunta) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", enquete=" + ", descricao=" + descricao + ", tipoPergunta="
				+ tipoPergunta + "]";
	}

 
	

	
	
	
	
	
	
	
}
