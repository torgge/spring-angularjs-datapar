package patterns.decodator;

public class DecoradorBarraHorizontal extends JanelaDecorator{
	
	public DecoradorBarraHorizontal(Janela janelaDecorada) {
		super(janelaDecorada);
	}

	public void draw() {
		drawBarraVertical();
		janelaDecorada.draw();
	}

	private void drawBarraVertical() { 
		System.out.println("desenha uma barra horizontal na janela"); }
}
