package model.jpa.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Mercaderia;

public class MercaderiaParametros {
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("topicos_avancados");
		EntityManager manager = factory.createEntityManager();

		Query query = manager.createNamedQuery("Mercaderia.findByQuantidadeMaiorQue");
		
		query.setParameter("quantidade", new BigDecimal(0));
		
		@SuppressWarnings("unchecked")
		List<Mercaderia> mercaderias = query.getResultList();
		
		for (Mercaderia mercaderia : mercaderias) {
			System.out.println("Nome: " + mercaderia.getNome());
			System.out.println("Quantidade: " + mercaderia.getQuantidade());
		}
		
		manager.close();
		factory.close();
	}
}