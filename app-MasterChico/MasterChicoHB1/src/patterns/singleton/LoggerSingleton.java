package patterns.singleton;

public class LoggerSingleton {

	private static LoggerSingleton instanciaUnica;

	public static LoggerSingleton getInstance() {

		if (instanciaUnica == null) {
			instanciaUnica = new LoggerSingleton();
		}
		
		return instanciaUnica;
		
	}

}
