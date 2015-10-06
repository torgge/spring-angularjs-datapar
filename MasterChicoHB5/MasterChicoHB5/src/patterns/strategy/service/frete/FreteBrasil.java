package patterns.strategy.service.frete;

import patterns.strategy.service.Frete;

public class FreteBrasil implements Frete {

	public double calcularPreco(int distancia) {
		return distancia * 1.5 + 10;
	}
	
}