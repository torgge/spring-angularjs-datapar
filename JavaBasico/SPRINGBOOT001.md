```java

@SpringBootApplication
@Controller
public class TestApplication {

	@RequestMapping("/")
	public String home() {
		return "forward:/test.html";
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(TestApplication.class).properties(
				"server.port=9999", "security.basic.enabled=false").run(args);
	}

}

```

source: https://spring.io/blog/2015/05/19/testing-an-angular-application-angular-js-and-spring-security-part-viii
