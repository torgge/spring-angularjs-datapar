package py.com.datapar.enquete.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tab_enquete_pergunta")
public class EnquetePergunta {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="enquete_id")
	private Enquete enquete;
	
	@ManyToOne
	@JoinColumn(name="pergunta_id")
	private Pergunta pergunta;

	
//	@JsonProperty("opcoes")
//	@OneToMany(mappedBy="enquete", fetch = FetchType.LAZY)
//	private List<EnquetePergunta> listaEnquetePergunta;
	
	
//	public List<EnquetePergunta> getListaEnquetePergunta() {
//		return listaEnquetePergunta;
//	}

//	public void setListaEnquetePergunta(List<EnquetePergunta> listaEnquetePergunta) {
//		this.listaEnquetePergunta = listaEnquetePergunta;
//	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Enquete getEnquete() {
		return enquete;
	}

	public void setEnquete(Enquete enquete) {
		this.enquete = enquete;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public EnquetePergunta(long id, Enquete enquete, Pergunta pergunta) {
		super();
		this.id = id;
		this.enquete = enquete;
		this.pergunta = pergunta;
	}
	
	
	public EnquetePergunta(){
	}

	@Override
	public String toString() {
		return "EnquetePergunta [id=" + id + ", enquete=" + enquete + ", pergunta=" + pergunta + "]";
	}
	
	
	
}
