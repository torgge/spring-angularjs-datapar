package py.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoDemo1Application {

	
	@Autowired
	private ProdutoRepository repository;

	
    public static void main(String[] args) {
        SpringApplication.run(MongoDemo1Application.class, args);
    }
    
    public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Produto("Banana"));
		repository.save(new Produto("Laranja"));
		repository.save(new Produto("Uva"));

		// fetch all customers
		System.out.println("Lista de produtos");
		System.out.println("-----------------");
		for (Produto produto: repository.findAll()) {
			System.out.println(produto);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("findByNome('Banana'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByNome("Banana"));


	}
}
