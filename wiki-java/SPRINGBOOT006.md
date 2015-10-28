## Configurando Oracle com Spring Boot

Adicione a dependência

```java

	<repositories>
		<repository>
			<id>codelds</id>
			<url>https://code.lds.org/nexus/content/groups/main-repo</url>
		</repository>
	</repositories>
	
	
	<dependency>
		<groupId>com.oracle</groupId>
		<artifactId>ojdbc6</artifactId>
		<version>11.2.0.3</version>
	</dependency>


```

No *apllication.properties* adicione:

```

spring.datasource.url=jdbc:oracle:thin:@[ip-servidor]:1521:[seed]
spring.datasource.username=[usuario]
spring.datasource.password=[senha]

```
