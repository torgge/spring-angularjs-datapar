package patterns.decodator;

/**
 * Decorator
 * 
 * O Padr�o Decorator anexa responsabilidades adicionais a um objeto dinamicamente. 
 * Os decoradores fornecem uma alternativa flex�vel de subclasse para estender a funcionalidade.
 * 
 * - Decoradores t�m o mesmo supertipo que os objetos que eles decoram;
 * - Podemos usar um ou mais decoradores para englobar um objeto;
 * - Podemos passar um objeto decorado no lugar do objeto original (englobado);
 * - O decorador adiciona seu pr�prio comportamento antes e/ou depois de delegar o objeto que ele decora o resto do trabalho;
 * - Os objetos podem ser decorados din�micamente com v�rios decoradores.
 * 
 * @author lyndon-pc
 *
 */
public class DecoradorTeste {
	public static void main(String args[]) {
		Janela janelaDecorada = new DecoradorBarraHorizontal( 
				new DecoradorBarraVertical(new JanelaSimples()));
		janelaDecorada.draw();
	}
}
