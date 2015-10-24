package hello;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
     
    	// SpringApplication.run(Application.class, args);
    	
       new SpringApplicationBuilder( Application.class).properties(
                "server.port=5000" ).run(args);    	
    	
    }
}
