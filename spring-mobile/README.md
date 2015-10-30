## Spring Mobile

###(I) Adicione a dependência

```java

<dependency>
    <groupId>org.springframework.mobile</groupId>
    <artifactId>spring-mobile-device</artifactId>
    <version>${org.springframework.mobile-version}</version>
</dependency>

<repository>
    <id>springsource-milestone</id>
    <name>SpringSource Milestone Repository</name>
    <url>http://repo.springsource.org/milestone</url>
</repository>

```
 Versão milestone.Exemlo: 1.0.0.RC2. Adicione:
 
```java

<repository>
    <id>springsource-milestone</id>
    <name>SpringSource Milestone Repository</name>
    <url>http://repo.springsource.org/milestone</url>
</repository>

```

Versão nightly. Adicione:

```java

<repository>
    <id>springsource-snapshot</id>
    <name>SpringSource Snapshot Repository</name>
    <url>http://repo.springsource.org/snapshot</url>
</repository>
            

``` 
##(II) Utilização

```java

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public void home(Device device) {
        if (device.isMobile()) {
            logger.info("Hello mobile user!");      
        } else {
            logger.info("Hello desktop user!");         
        }
    }

``` 


http://docs.spring.io/autorepo/docs/spring-mobile/1.0.x/reference/htmlsingle/

