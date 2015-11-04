package com.idomine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idomine.model.enums.Genero;

@Entity
@Table(name="tab_livro")
public class Livro {

	@Id
	@GeneratedValue
	@JsonProperty("id")
	private long id;
	
	@Column(name="titulo",length=100,nullable=false,unique=true)
	private String titulo;

	
	@ManyToOne
	@JoinColumn(name="autor_id")
	private Autor autor;
	
	private Genero genero;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero + "]";
	}

	public Livro(long id, String titulo, Autor autor, Genero genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
	}

	public Livro() {
		super();
	} 
	
	
	
}
