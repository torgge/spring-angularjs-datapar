package daoImpl.teste;

import java.math.BigDecimal;
import java.util.List;

import daoImpl.MercaderiaDaoImpl;
import model.Mercaderia;

public class TesteCriteria2 {

	
	public static void main(String[] args) {

		List<Mercaderia> listaMercaderias = new MercaderiaDaoImpl().findByProperty( Mercaderia.class, "quantidade", new BigDecimal(100) );
		
		for (Mercaderia m : listaMercaderias) {
			System.out.println(m);
		}
		
	}
}
