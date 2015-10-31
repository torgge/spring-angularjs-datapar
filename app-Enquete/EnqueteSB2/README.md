## Gerando WAR

Execute:

```java

mvn clean install

```
Copie o war da pasta target para webapps do tomcat:

```java

copy MYAPP.war c:\tomcat\webapps

```

## (II) Configuração pela linha de comando

Por padrão o Aplicação Spring Boot irá converter os argumentos da linha comando
em propriedaeds de configuração da aplicação. Esse modo de ajustar propriedades
tem o mais alto nível de prioridade. Exemplo:

```
 	java -jar MyApp-1.0.jar 
		 --server.port=5000 
		 --spring.datasource.username=LYNDON
		 --spring.datasource.password=1234
		 --spring.datasource.url=jdbc:oracle:thin:@[ip-server]:1521:[seed]
		 
```
