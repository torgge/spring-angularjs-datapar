package patterns.singleton;

/**
 * Padr�o Singleton
 * 
 * O padr�o Singleton garante que se tenha uma �nica classe 
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
