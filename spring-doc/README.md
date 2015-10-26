## Spring DOC

Documentação de API com Spring Swagger.

### (I)  Adicione a dependência ao projeto.

```java

		<dependency>
			<groupId>com.mangofactory</groupId>
			<artifactId>swagger-springmvc</artifactId>
			<version>1.0.2</version>
			<type>jar</type>
		</dependency>
		
```

### (II) Acrescente a classe de configuração do Swagger

```java

@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {
    
    private SpringSwaggerConfig springSwaggerConfig;
 
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }
    
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                //Root level documentation
                .apiInfo( new ApiInfo(
                	              "MasterChico API 0.",
                	              "API REST do Applicativo de Controle de Pedidos",
                	              "API terms of service",
                	              "integraldominio@gmail.com",
                	              "API Licence Type",
                	              "API License URL"
                	        ))
                .useDefaultResponseMessages(false)
                //Map the specific URL patterns into Swagger
                .includePatterns("/api","/api/.*");
    }
    
}

```

Observação: Esta classe deve está um nível acima das classes de anotadas com @RestCrotroller. Se não, usar a anotação abaixo no lugar de @EnableAutoConfiguration.

```java

@ComponenScan(basePackages = { "py.com.datapar.aplicacao.resource" })

```

