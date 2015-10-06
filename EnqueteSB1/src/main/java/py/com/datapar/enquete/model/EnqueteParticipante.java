package py.com.datapar.enquete.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tab_enquete_participante")
public class EnqueteParticipante {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="enquete_id")
	private Enquete enquete;

	@ManyToOne
	@JoinColumn(name="participante_id")
	private Participante participante;

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

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public EnqueteParticipante(){
		
	}
	
	public EnqueteParticipante(long id, Enquete enquete, Participante participante) {
		super();
		this.id = id;
		this.enquete = enquete;
		this.participante = participante;
	}

	@Override
	public String toString() {
		return "EnqueteParticipante [id=" + id + ", enquete=" + enquete + ", participante=" + participante + "]";
	}
	
	
	
	
	
}
