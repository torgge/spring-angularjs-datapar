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

```

### (IV) Utilizando

```java

```


