package model.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RemoveProdutos {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("topicos_avancados");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager
				.createQuery("DELETE Produto p WHERE p.preco < 50000");
		query.executeUpdate();
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
