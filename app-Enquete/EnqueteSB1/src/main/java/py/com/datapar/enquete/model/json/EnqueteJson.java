package py.com.datapar.enquete.model.json;

import java.util.List;

public class EnqueteJson {

	private long id;

	private String pergunta;
	
	private List<String> opcoes;
	
	private String resposta;
	
	public EnqueteJson(){
	}
	
	public EnqueteJson(long id, String pergunta, List<String>  opcoes, String resposta) {
		super();
		this.id = id;
		this.pergunta = pergunta;
		this.opcoes = opcoes;
		this.resposta = resposta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public List<String>  getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<String>  opcoes) {
		this.opcoes = opcoes;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Override
	public String toString() {
		return "EnqueteJson [id=" + id + ", pergunta=" + pergunta + ", opcoes=" + opcoes + ", resposta=" + resposta
				+ "]";
	}

	
	
}
