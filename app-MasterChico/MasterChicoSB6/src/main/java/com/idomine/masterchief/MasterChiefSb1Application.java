package com.idomine.masterchief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.idomine.masterchief.resource.CategoriaResource;
import com.idomine.masterchief.resource.FornecedorResource;
import com.idomine.masterchief.resource.MercaderiaResource;
import com.idomine.masterchief.resource.PedidoItemResource;
import com.idomine.masterchief.resource.PedidoResource;
import com.idomine.masterchief.resource.UnidadeResource;
import com.mangofactory.swagger.plugin.EnableSwagger;

@SpringBootApplication
public class MasterChiefSb1Application {

    public static void main(String[] args) {
    	
    	ConfigurableApplicationContext app =
    			SpringApplication.run(MasterChiefSb1Application.class, args);

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
