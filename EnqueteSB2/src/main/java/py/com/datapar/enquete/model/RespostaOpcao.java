package py.com.datapar.enquete.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tab_resposta_opcao")
public class RespostaOpcao {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="enquetePergunta_id")
	private EnquetePergunta enquetePergunta;
	
	@ManyToOne
	@JoinColumn(name="participante_id")
	private Participante participante;

	private String resposta;

	public EnquetePergunta getEnquetePergunta() {
		return enquetePergunta;
	}

	public void setEnquetePergunta(EnquetePergunta enquetePergunta) {
		this.enquetePergunta = enquetePergunta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	@Override
	public String toString() {
		return "RespostaOpcao [id=" + id + ", perguntaOpcao=" + enquetePergunta + ", participante=" + participante + "]";
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public RespostaOpcao(long id, EnquetePergunta enquetePergunta, Participante participante, String resposta) {
		super();
		this.id = id;
		this.enquetePergunta = enquetePergunta;
		this.participante = participante;
		this.resposta = resposta;
	}
	
	
	
	
}
