package MasterChicoSB1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import MasterChicoSB1.resource.CategoriaResource;
import MasterChicoSB1.resource.MercaderiaResource;
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
    	
    	
        
    }
}
