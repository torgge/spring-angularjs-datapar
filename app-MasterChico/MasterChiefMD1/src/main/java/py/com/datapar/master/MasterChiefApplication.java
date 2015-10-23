package py.com.datapar.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import py.com.datapar.master.resource.CategoriaResource;
import py.com.datapar.master.resource.FornecedorResource;
import py.com.datapar.master.resource.MercaderiaResource;
import py.com.datapar.master.resource.PedidoItemResource;
import py.com.datapar.master.resource.PedidoResource;
import py.com.datapar.master.resource.UnidadeResource;

@SpringBootApplication
public class MasterChiefApplication {

    public static void main(String[] args) {
    	
    	ConfigurableApplicationContext app =
    			SpringApplication.run(MasterChiefApplication.class, args);

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
