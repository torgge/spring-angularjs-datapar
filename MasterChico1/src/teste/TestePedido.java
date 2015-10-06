package teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Fornecedor;
import model.Mercaderia;
import model.Pedido;
import model.PedidoItem;

public class TestePedido {

	public static void main(String[] args) {

		List<PedidoItem> listaItensDoPedido = new ArrayList<PedidoItem>();
		long sequencialItemPedido =1;
		
		for (int j = 1; j <= 2; j++) {

			Pedido pedido = new Pedido();
			pedido.setId(j); 
			pedido.setData(new Date());
			pedido.setSituacao("A");
			pedido.setFornecedor(new Fornecedor(1, "Fornecedor" + j, "9999-000" + j));

			for (int i = 1; i <= 10; i++) {
				Mercaderia mercaderia = new Mercaderia(i, "Mercaderia-" + i, new BigDecimal(0), new BigDecimal(0), null,
						null);
				listaItensDoPedido.add(new PedidoItem(sequencialItemPedido, pedido, mercaderia, new BigDecimal(10), new BigDecimal(10)));
				
				//incrementa sequencia pedido
				sequencialItemPedido++;
			}

		}

		System.out.println(listaItensDoPedido);

	}

}
