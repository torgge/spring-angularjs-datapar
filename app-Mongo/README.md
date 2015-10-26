## MongoDB 

### (I) Adicionar 

```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-mongodb</artifactId>
	</dependency>
```

### (II) Mapeamento

Basta adicionar a anotação @Id. Exemplo:

```java

package py.com;
import org.springframework.data.annotation.Id;

public class Produto {

	@Id
	private String id;

	private String nome;

	// getters and setters
}

```

### (III) Repository

```java
package py.com;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

	public List<Produto> findByNome(String nome);
	
	
}
```

### (IV) Utilizando

```java
 public void run(String... args) throws Exception {

		repository.deleteAll();

		// save 
		repository.save(new Produto("Banana"));
		repository.save(new Produto("Laranja"));
		repository.save(new Produto("Uva"));

		// fetch 1 
		System.out.println("Lista de produtos");
		System.out.println("-----------------");
		for (Produto produto: repository.findAll()) {
			System.out.println(produto);
		}
		System.out.println();

		// fetch 2
		System.out.println("findByNome('Banana'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByNome("Banana"));
	}
```


