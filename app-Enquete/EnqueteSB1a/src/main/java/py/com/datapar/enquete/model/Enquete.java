package py.com.datapar.enquete.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * O jointable cria a tabela relação
 * @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
 * @JoinTable(name="tab_enquete_perguntas", joinColumns={@JoinColumn(name="enquete_id", referencedColumnName="id")}, 
 * inverseJoinColumns={@JoinColumn(name="pergunta_id", referencedColumnName="id")})
 * 
 * @author Lyndon Tavares
 *
 */

@Entity
@Table(name="tab_enquete")
public class Enquete {

	@Id
	@GeneratedValue
	private long id;
	
	private String nome;
	
	private String descricao;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="data_inicio")
	private Date dataInicio;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="data_termino")
	private Date dataTermino;
	
	@Enumerated(value=EnumType.STRING)
	private TipoResultado tipoResultado;

	@OneToMany(mappedBy="enquete")
	private List<EnqueteParticipante> listaParticipante;
	
	@OneToMany(mappedBy="enquete")
	private List<EnquetePergunta> listaPergunta;
	

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

 	public List<EnqueteParticipante> getListaParticipante() {
		return listaParticipante;
	}

	public void setListaParticipante(List<EnqueteParticipante> listaParticipante) {
		this.listaParticipante = listaParticipante;
	}

	public List<EnquetePergunta> getListaPergunta() {
		return listaPergunta;
	}

	public void setListaPergunta(List<EnquetePergunta> listaPergunta) {
		this.listaPergunta = listaPergunta;
	}

	public TipoResultado getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(TipoResultado tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	public Enquete(){
	}
	
	
	

	public Enquete(long id, String nome, String descriçao, Date dataInicio, Date dataTermino,
			TipoResultado tipoResultado, List<EnqueteParticipante> listaParticipante, List<EnquetePergunta> listaPergunta) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descriçao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.tipoResultado = tipoResultado;
		this.listaParticipante = listaParticipante;
		this.listaPergunta = listaPergunta;
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
		Enquete other = (Enquete) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enquete [id=" + id + ", nome=" + nome + ", descriçao=" + descricao + ", dataInicio=" + dataInicio
				+ ", dataTermino=" + dataTermino + ", tipoResultado=" + tipoResultado + ", listaParticipante="
				+ listaParticipante + ", listaPergunta=" + listaPergunta + "]";
	}

	
	
	
}
