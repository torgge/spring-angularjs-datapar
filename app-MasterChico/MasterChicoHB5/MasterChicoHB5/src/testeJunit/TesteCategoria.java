package testeJunit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Categoria;

public class TesteCategoria {

	@Test
	public void testeCateoriaEquals() {

		Categoria cat1 = new Categoria(1,"Categoria 1");
		
		Categoria cat2 = new Categoria(1,"Categoria 2");
		
		//Aqui testamos que os ojetos acima s�o iguais
		//pois o m�todo equals testa somente o id
		assertEquals( true , cat1.equals(cat2) );
	}

	
	
}
