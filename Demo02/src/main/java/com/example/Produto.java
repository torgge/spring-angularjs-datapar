package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_produto")
public class Produto {

	@Id
	@GeneratedValue
	private long id;

	private String nome;

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
	
	Produto(){
		
	}

	public Produto(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	
	
}
