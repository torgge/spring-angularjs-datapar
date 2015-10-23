package patterns.singleton;

/**
 * Padrão Singleton
 * 
 * O padrão Singleton garante que se tenha uma única classe 
 * de um determinado objeto para todo o sistema.
 * 
 * @author Lyndon Tavares
 *
 */
public class Exemplo {

	public static void main(String[] args) {
	
		LoggerSingleton logger = new LoggerSingleton();
		
		System.out.println( logger.getInstance().toString() );
		
		LoggerSingleton logger2 = new LoggerSingleton();
		
		System.out.println( logger2.getInstance().toString() );
		
	}
	
	
}
