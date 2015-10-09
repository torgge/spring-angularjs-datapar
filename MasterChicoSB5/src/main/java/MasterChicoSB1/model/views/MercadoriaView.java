package MasterChicoSB1.model.views;

/**
 * Exemplo de utilização de visõescustomizadas através de serialização de 
 * objetos com @HsonView da Jackson
 * 
 * Explicação:
 * 
 * Neste exemplo são criados 3 views. Detalhado e Gerencial extendem resumo.
 * Implcações:
 * Resumo a view mais restritiva
 * Detalhado e Gerencial são alternativas.
 * 
 * Utilização:
 * 
 * Passo 1:
 * Aplique a anotação @JsonView ao atributo da classe mapeada.
 * Veja o exemplo na classe Mercaderia 
 * 
 * Passo 2: 
 * Aplique a anotação @JsonView a um endpoint em uma classe de controle.
 * Veja a classe MercaderiaResource 
 *  
 * 
 * @author Lyndon Tavares
 *
 */
public class MercadoriaView {

	public interface Resumo {}
	public interface Detalhado extends Resumo {}
	public interface Gerencial extends Resumo {}
	
}
