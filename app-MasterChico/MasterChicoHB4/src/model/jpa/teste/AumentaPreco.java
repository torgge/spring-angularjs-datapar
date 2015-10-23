package model.jpa.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AumentaPreco {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("topicos_avancados");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager
				.createQuery("UPDATE Mercaderia m SET m.quantidade = 1000");
		query.executeUpdate();
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
