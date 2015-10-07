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
	@JoinColumn(name="pergunta_opcao_id")
	private PerguntaOpcao perguntaOpcao;
	
	@ManyToOne
	@JoinColumn(name="participante_id")
	private Participante participante;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PerguntaOpcao getPerguntaOpcao() {
		return perguntaOpcao;
	}

	public void setPerguntaOpcao(PerguntaOpcao perguntaOpcao) {
		this.perguntaOpcao = perguntaOpcao;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	@Override
	public String toString() {
		return "RespostaOpcao [id=" + id + ", perguntaOpcao=" + perguntaOpcao + ", participante=" + participante + "]";
	}
	
	
	
	
}
