package testeJunit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Unidade;

public class TesteUnidade {

	@Test
	public void testeConstrutorComParametros() {

		Unidade unidade = new Unidade(1,"Kg");
		
		//aqui testamos a atribuição pelo construtor
		//para o atributo descricao
		assertEquals( "Kg", unidade.getDescricao() );
	}

}
