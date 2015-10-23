package model.jpa.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Mercaderia;

 

public class MercaderiaQuery {

	
public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("topicos_avancados");
		
		EntityManager manager = factory.createEntityManager();
		
		Query query = manager.createQuery("SELECT e FROM Mercaderia e");
		
		@SuppressWarnings("unchecked")
		List<Mercaderia> editoras = query.getResultList();
		
		for (Mercaderia e : editoras) {
			System.out
					.println(e.getNome() );
		}
		
		manager.close();
		factory.close();
	}

	
}
