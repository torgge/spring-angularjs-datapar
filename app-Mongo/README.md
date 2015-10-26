## MongoDB 

### Adicionar 

```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-mongodb</artifactId>
	</dependency>
```

### Mapeamento

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

### Repository

```java

```

### Utilizando

```java

```


