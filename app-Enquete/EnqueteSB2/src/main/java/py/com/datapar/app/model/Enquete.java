package py.com.datapar.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * JOINTABLE cria a tabela relação
 * 
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
	
	private boolean enviarEmail;
	
	private long tempoEmail;
	
	private TipoEnqueteStatus status;
	
	@ManyToOne
	@JoinColumn(name="autor_id")
	private Participante autor;

	//@JsonIgnore
	//@OneToMany(mappedBy="enquete", fetch = FetchType.LAZY)
	//private List<EnqueteParticipante> listaParticipante;
	
	//@JsonIgnore
	//@OneToMany(mappedBy="enquete", fetch = FetchType.LAZY)
	//private List<EnquetePergunta> listaPergunta;
	
	public boolean isEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public long getTempoEmail() {
		return tempoEmail;
	}

	public void setTempoEmail(long tempoEmail) {
		this.tempoEmail = tempoEmail;
	}

	private boolean ativa;
	

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


	public TipoResultado getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(TipoResultado tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	public Enquete(){
	}
	
	
	

	public Enquete(long id, String nome, String descriçao, Date dataInicio, Date dataTermino,
			TipoResultado tipoResultado, List<EnqueteParticipante> listaParticipante, List<EnquetePergunta> listaPergunta, boolean ativa) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descriçao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.tipoResultado = tipoResultado;
		//this.listaParticipante = listaParticipante;
		//this.listaPergunta = listaPergunta;
		this.ativa = ativa;
		this.enviarEmail=true;
		this.tempoEmail=1;
		this.status = TipoEnqueteStatus.AGUARDANDO;
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
				 + "]";
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public TipoEnqueteStatus getStatus() {
		return status;
	}

	public void setStatus(TipoEnqueteStatus status) {
		this.status = status;
	}

	public Participante getAutor() {
		return autor;
	}

	public void setAutor(Participante autor) {
		this.autor = autor;
	}

	
	
	
}
