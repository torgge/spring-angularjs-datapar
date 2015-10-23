package collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Mercaderia;

public class TesteCollections {

	public static void main(String[] args) {

		List<Mercaderia> listaMercaderia = new ArrayList<Mercaderia>();

		for (int i = 1; i <= 10; i++) {

			Random gerador = new Random();
			int numero = gerador.nextInt(10);

			Mercaderia m = new Mercaderia();
			m.setId(1);
			m.setNome("mercaderia 1");
			m.setQuantidade(new BigDecimal(numero));

			listaMercaderia.add(m);

		}
		
		Collections.sort( listaMercaderia );
		System.out.println("Mercadorias ordenadas por Quantidade");
		for (Mercaderia mercaderia : listaMercaderia) {
			System.out.println(mercaderia);
			
		};
		
		
		System.out.println("Mercadorias ordenadas por Quantidade descendente");
		Collections.reverse( listaMercaderia );
		for (Mercaderia mercaderia : listaMercaderia) {
			System.out.println(mercaderia);
			
		};
		
		

	}

}
