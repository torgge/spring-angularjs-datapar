package daoImpl.teste;

import java.util.List;

import dao.GenericDaoImpl.MatchMode;
import daoImpl.MercaderiaDaoImpl;
import model.Mercaderia;

public class TesteCriteria {

	
	public static void main(String[] args) {

		List<Mercaderia> listaMercaderias = new MercaderiaDaoImpl().findByProperty( Mercaderia.class, "nome", "100", MatchMode.END );
		
		for (Mercaderia m : listaMercaderias) {
			System.out.println(m);
		}
		
	}
}
