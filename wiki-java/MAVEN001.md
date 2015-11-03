## (I) Criando repositório local

Criando repositório local para pacotes customizados.

execute o comando pelo bash, Ajustando os parâmetros para suas necessidade:

```xml

mvn deploy:deploy-file 
    -Durl=file:repo/ 
    -Dfile=c:/idomine/lib/security.jar 
    -DgroupId=com.idomine 
    -DartifactId=security 
    -Dpackaging=jar 
    -Dversion=1.0

```

Adicione dependência no pom.xml:

```xml

    <repository>
        <id>project.local</id>
        <name>project</name>
        <url>file:${project.basedir}/repo</url>
    </repository>

<dependency>
    <groupId>con.idomine</groupId>
    <artifactId>security</artifactId>
    <version>1.0</version>
</dependency>

```
