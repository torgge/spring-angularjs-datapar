package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        
    	
    	ConfigurableApplicationContext app =    SpringApplication.run(DemoApplication.class, args);
    	
    	ProdutoRepository produto = app.getBean(ProdutoRepository.class);
    	
    	Produto p1 = new Produto();
    	
    	p1.setNome("produto-1");
    	
    	//produto.save(p1);
    	
    	for (int i=1; i<100; i++){
    		produto.save( new Produto( 0,"produto-"+i ));
    	}
    	
    	
    	
    }
}
