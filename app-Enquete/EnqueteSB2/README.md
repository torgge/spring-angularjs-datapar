## (I) Gerando WAR

Execute:

```java

mvn clean install

```
Copie o war da pasta target para webapps do tomcat:

```java

copy MYAPP.war c:\tomcat\webapps

```

## (II) Configuração pela linha de comando

Por padrão a Aplicação Spring Boot irá converter os argumentos da linha de comando
em propriedaeds de configuração da aplicação. Esse modo de ajustar propriedades
tem o mais alto nível de prioridade. Exemplo:

```
 	java -jar MyApp-1.0.jar 
		 --server.port=5000 
		 --spring.datasource.username=LYNDON
		 --spring.datasource.password=1234
		 --spring.datasource.url=jdbc:oracle:thin:@[ip-server]:1521:[seed]
		 
```

## (III) Configuração do pom.xml

Removendo arquivos e pastas 
Para remover arquivos e pastas durante geração do artefato (jar ou war) acrescente conforme exemplo abaixo.

```java

	<build>

		<resources>
			<resource>
				<!-- this is relative to the pom.xml directory -->
				<directory>src/main/resources/static</directory>
				<!-- there's no default value for this -->
				<excludes>
					<exclude>**/node_modules/**</exclude>
					<exclude>**/build/**</exclude>
					<exclude>**/json/**</exclude>
					<exclude>**/README.md</exclude>
					<exclude>**/gulpfile.js</exclude>
					<exclude>**/package.json</exclude>
					<exclude>**/apidoc/**</exclude>
					<exclude>**/api/**</exclude>
				</excludes>
			</resource>
		</resources>
		
		...
		...

	</build>
	
```
