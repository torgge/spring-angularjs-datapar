package com.integraldominio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pessoa {
 
    @Column(name = "name")
    private String name;
 
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa(String name) {
		super();
		this.name = name;
	}
 
	public Pessoa(){
		
	}
}