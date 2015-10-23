package MasterChicoSB1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import MasterChicoSB1.resource.CategoriaResource;
import MasterChicoSB1.resource.FornecedorResource;
import MasterChicoSB1.resource.MercaderiaResource;
import MasterChicoSB1.resource.PedidoItemResource;
import MasterChicoSB1.resource.PedidoResource;
import MasterChicoSB1.resource.UnidadeResource;

@SpringBootApplication
public class MasterChicoSb1Application {

    public static void main(String[] args) {
    	
    	ConfigurableApplicationContext app =
    			SpringApplication.run(MasterChicoSb1Application.class, args);

    	//pegar contexto e bean para execução de tarefas após iniciar...
    	CategoriaResource catgo = app.getBean(CategoriaResource.class);
    	catgo.gerar100(100);
    	
    	UnidadeResource unid = app.getBean(UnidadeResource.class);
    	unid.gerar100(100);
    	
    	MercaderiaResource merc = app.getBean(MercaderiaResource.class);
    	merc.gerar100(100);
    	
    	FornecedorResource forn = app.getBean(FornecedorResource.class);
    	forn.gerar100(100);
    	
    	PedidoResource pedi = app.getBean(PedidoResource.class);
    	pedi.gerar100(100);

    	PedidoItemResource item = app.getBean(PedidoItemResource.class);
    	item.gerar100(100);
       
    }
}
