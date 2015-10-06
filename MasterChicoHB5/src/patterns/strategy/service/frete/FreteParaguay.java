package patterns.strategy.service.frete;

import patterns.strategy.service.Frete;

public class FreteParaguay implements Frete {

	public double calcularPreco(int distancia) {
		return   distancia * 1 + 10;
	}
	
}
