package patterns.factory;


/**
 * Factory Method
 * 
 * De forma geral todos os padrões Factory (Simple Factory, Factory Method, Abstract Factory) 
 * encapsulam a criação de objetos.
 * 
 * @author Usuario1
 *
 */
public class TesteApp {

	public static void main(String args[]) {
		FactoryPessoa factory = new FactoryPessoa();
		String nome = "Lyndon Tavares";
		String sexo = "M";
		factory.getPessoa(nome, sexo);
	}
}
