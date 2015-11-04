package com.idomine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.idomine.model.Autor;
import com.idomine.model.Livro;
import com.idomine.model.enums.Genero;
import com.idomine.repository.AutorRepository;
import com.idomine.repository.LivroRepository;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {
     
    	ConfigurableApplicationContext app =
    				SpringApplication.run(BibliotecaApplication.class, args);
    	
    	AutorRepository autorRep = app.getBean(AutorRepository.class);
    	LivroRepository livroRep = app.getBean(LivroRepository.class);
    	

    	/**
    	 * adiconado registros de autores e livros
    	 */
    	
    	for ( int i=1;i<=10;i++){
    		Autor autor = autorRep.save( new Autor(0,"autor-"+i ));
    		for (int j=1;j<=10;j++){
    			livroRep.save( new Livro(j,"livro-"+j,autor,Genero.FICCAO));
    		}
    	}
    	
    	
    }
}
