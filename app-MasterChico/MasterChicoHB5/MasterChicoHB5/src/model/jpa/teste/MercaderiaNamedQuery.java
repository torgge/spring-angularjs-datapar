package model.jpa.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Mercaderia;

public class MercaderiaNamedQuery {
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("topicos_avancados");
		
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createNamedQuery("Mercaderia.findAll");
		
		@SuppressWarnings("unchecked")
		List<Mercaderia> mercaderias = query.getResultList();
		
		for (Mercaderia mercaderia : mercaderias) {
			System.out.println( mercaderia.getNome());
		}
		
		manager.close();
		factory.close();
	}
}