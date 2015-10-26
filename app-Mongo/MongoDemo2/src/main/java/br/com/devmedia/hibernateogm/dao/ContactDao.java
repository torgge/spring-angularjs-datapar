package br.com.devmedia.hibernateogm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.devmedia.hibernateogm.entity.Contact;
import br.com.devmedia.hibernateogm.util.JPAUtil;

public class ContactDao {
	
	public List<Contact> nativeQueryByAgeBetween(int start, int end) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		
		String mongoQuery = 
				"{$query : " +
				  "{'age' : {'$gte' : " + start + ", '$lte' : " + end + "} }, " +
				  "$orderby : {'full_name' : 1}" +		
				"}";
		
		Query query = em.createNativeQuery(mongoQuery, Contact.class);
		
		List<Contact> contacts = query.getResultList();		
		em.close();
		return contacts;
	}
	
	public List<Contact> findAll() {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		Query query = em.createQuery("from Contact");
		List<Contact> contacts = query.getResultList();
		em.close();
		return contacts;
	}
	
	public Contact findByName(String name) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		Query query = em.createQuery("from Contact c where c.name like :name");
		query.setParameter("name", name);
		Contact contact = (Contact) query.getSingleResult();
		em.close();
		return contact;
	}
	
	public void update(Contact contact) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.merge(contact);
		em.getTransaction().commit();
		em.close();
	}
	
	public void save(Contact contact) {
		EntityManager em = JPAUtil.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.persist(contact);
		em.getTransaction().commit();
		em.close();
	}
}
