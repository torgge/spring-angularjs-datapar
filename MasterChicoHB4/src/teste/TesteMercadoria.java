package teste;

import java.math.BigDecimal;

import model.Categoria;
import model.Mercaderia;
import model.Unidade;

public class TesteMercadoria {

	public static void main(String[] args) {
		
		Mercaderia m = new Mercaderia();
		m.setId(1);
		m.setNome("mercaderia 1");
		m.setQuantidade(new BigDecimal(10));
		m.setQuantidadeMinima(new BigDecimal(10));
		m.setUnidade(new Unidade(1,"Unidade 1"));
		m.setCategoria(new Categoria(1,"Categoria 1"));
		
		System.out.println(m);
		
	}
	
}
