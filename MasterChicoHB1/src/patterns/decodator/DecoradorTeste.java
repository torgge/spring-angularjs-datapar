package patterns.decodator;

/**
 * Decorator
 * 
 * O Padrão Decorator anexa responsabilidades adicionais a um objeto dinamicamente. 
 * Os decoradores fornecem uma alternativa flexível de subclasse para estender a funcionalidade.
 * 
 * - Decoradores têm o mesmo supertipo que os objetos que eles decoram;
 * - Podemos usar um ou mais decoradores para englobar um objeto;
 * - Podemos passar um objeto decorado no lugar do objeto original (englobado);
 * - O decorador adiciona seu próprio comportamento antes e/ou depois de delegar o objeto que ele decora o resto do trabalho;
 * - Os objetos podem ser decorados dinâmicamente com vários decoradores.
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
