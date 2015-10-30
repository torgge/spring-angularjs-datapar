## Spring Mobile

###(I) Adicione a dependência

```java

<dependency>
    <groupId>org.springframework.mobile</groupId>
    <artifactId>spring-mobile-device</artifactId>
    <version>${org.springframework.mobile-version}</version>
</dependency>

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

Exemplo 1:

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

Exemplo 2:

```java

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(Device device, Model model) {
		if (device == null) {
			logger.info("no device detected");
		} else if (device.isNormal()) {
			logger.info("Device is normal");
		} else if (device.isMobile()) {
			logger.info("Device is mobile");
		} else if (device.isTablet()) {
			logger.info("Device is tablet");
		}
		return "home";
	}

}

```

http://docs.spring.io/autorepo/docs/spring-mobile/1.0.x/reference/htmlsingle/

