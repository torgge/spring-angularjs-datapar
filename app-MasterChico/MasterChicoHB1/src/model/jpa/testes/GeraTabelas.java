/**
 * @aluno Lyndon Tavares 
 */
package model.jpa.testes;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelas {
	public static void main(String[] args) { 
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("topicos_avancados"); 
		factory.close(); 
}}