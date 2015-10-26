package br.com.devmedia.hibernateogm;

import java.util.Arrays;
import java.util.List;

import br.com.devmedia.hibernateogm.dao.ContactDao;
import br.com.devmedia.hibernateogm.entity.Address;
import br.com.devmedia.hibernateogm.entity.Contact;
import br.com.devmedia.hibernateogm.entity.Phone;

/**
 * Hello world!
 *
 */
public class AppHibernateOGM {
    
	public static void main( String[] args ) {
        System.out.println( "Pocket Vídeo DevMedia!" );
        
        //save();
        
        //queries();
        
        update();
    }

	private static void update() {
		
		Contact c1 = new ContactDao().findByName("Ana Maria da Silva");
		System.out.println(c1.toString());		
		
		c1.setName("Ana Lucia da Silva");
		c1.setTags(Arrays.asList("leitura", "filmes", "seriados"));
		
		new ContactDao().update(c1);
		
		Contact c2 = new ContactDao().findByName("Ana Lucia da Silva");
		System.out.println(c2.toString());
	}

	private static void queries() {
		
		//Contact c1 = new ContactDao().findByName("Carlos Figueira");
		//System.out.println(c1.toString());
		
		//List<Contact> c2 = new ContactDao().findAll();
		//c2.forEach(System.out::println);
		
		//List<Contact> c3 = new ContactDao().nativeQueryByAgeBetween(18, 29);
		//c3.forEach(System.out::println);
	}

	private static void save() {
		
		Contact c1 = new Contact();
		c1.setName("Ana Maria da Silva");
		c1.setAge(21);
		//new ContactDao().save(c1);
		
		Contact c2 = new Contact();
		c2.setName("Carlos Figueira");
		c2.setAge(32);
		c2.setAddress(new Address("Av. Copacabana. 98", "Rio de Janeiro"));
		//new ContactDao().save(c2);
		
		Contact c3 = new Contact();
		c3.setName("Joana Pires");
		c3.setAge(25);
		c3.setAddress(new Address("Av. Ipanema. 98", "Porto Alegre"));
		c3.setPhones(Arrays.asList(
			new Phone("Celular", "9191.8585"), new Phone("Comercial", "3222.5232"))
		);
		//new ContactDao().save(c3);
		
		Contact c4 = new Contact();
		c4.setName("Pedro Barbosa");
		c4.setAge(35);
		c4.setAddress(new Address("Rua das Oliveiras", "Porto Alegre"));
		c4.setPhones(Arrays.asList(
			new Phone("Celular", "9191.8080"), new Phone("Comercial", "3222.0230"))
		);
		c4.setTags(Arrays.asList("filmes", "novelas", "futebol"));		
		//new ContactDao().save(c4);
		
	}
	
	
}
