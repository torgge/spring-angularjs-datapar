package daoImpl.teste;

import java.math.BigDecimal;
import java.util.List;

import daoImpl.MercaderiaDaoImpl;
import model.Mercaderia;

public class TesteDao {

	
	public static void main(String[] args) {
		
		
		MercaderiaDaoImpl dao = new MercaderiaDaoImpl();
		Mercaderia mercaderia = new Mercaderia();
		mercaderia.setNome("Mercadedia-DAO01");
		mercaderia.setQuantidade(new BigDecimal(1000));

		dao.save(mercaderia);
		
		List<Mercaderia> listaMercaderias = new MercaderiaDaoImpl().getAll(Mercaderia.class);
		
		for (Mercaderia m : listaMercaderias) {
			System.out.println(m);
		}
		
		
	}
}
