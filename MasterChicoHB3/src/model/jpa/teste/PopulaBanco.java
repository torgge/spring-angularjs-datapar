package model.jpa.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Mercaderia;

public class PopulaBanco {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("topicos_avancados");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		for (int i = 0; i < 100; i++) {
		
			Mercaderia p = new Mercaderia();
			p.setNome("Mercaderia " + i);
			p.setQuantidade( new BigDecimal( i * 10.0 ));
			manager.persist(p);
		}
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}