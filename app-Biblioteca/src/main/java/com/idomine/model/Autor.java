package com.idomine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tab_autor")
public class Autor {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="nome",length=100,nullable=false,unique=true)
	private String nome;
	
	@Transient
	private String nomeToUpperCase;

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

	public String getNomeToUpperCase() {
		return nomeToUpperCase;
	}

	public void setNomeToUpperCase(String nomeToUpperCase) {
		this.nomeToUpperCase = nomeToUpperCase;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", nomeToUpperCase=" + nomeToUpperCase + "]";
	}
	
	public Autor(){
	}

	public Autor(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	
	
}
