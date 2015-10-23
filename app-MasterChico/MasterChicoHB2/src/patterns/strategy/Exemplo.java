package patterns.strategy;

import patterns.strategy.service.frete.FreteBrasil;
import patterns.strategy.service.frete.FreteParaguay;


/**
 * Padr�o Strategy
 * 
 * Permite que o algoritmo varie independentemente dos cliente que 
 * o utilizam. Ele � aplicado sempre que se tem um algoritmo que 
 * pode ter diferentes comportamento, em vez de usar muitos 
 * comando condicionais.
 * 
 * @author Lyndon Tavares
 *
 */
public class Exemplo {

	
	public static void main(String[] args) {
		
			long distancia = 1000;
		
			double frete = new FreteBrasil().calcularPreco(100);
			
			System.out.printf("Dist�ncia(%d km) Total frete Brasil R$ %.2f \n", distancia, frete);
			
			frete = new FreteParaguay().calcularPreco(1000);
			
			System.out.printf("Dist�ncia(%d km) Total frete Paraguay G %.0f", distancia, frete);
		
	}
	
}
