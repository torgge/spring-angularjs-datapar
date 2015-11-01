##CORS - Cross-origin resource sharing 

Compartilhamento de recursos de origem cruzada é uma especificação de uma tecnologia de navegadores que define meios para um servidor permitir que seus recursos sejam acessados por uma página web de um domínio diferente.[1] Esse tipo de acesso seria de outra forma negado pela same origin policy. CORS define um meio pelo qual um navegador e um servidor web podem interagir para determinar se devem ou não utilizar/permitir requisições cross-origem[2] . É um acordo que permite grande flexibilidade, mas é mais seguro que permitir todos as requisições desse tipo.

> https://pt.wikipedia.org/wiki/Cross-origin_resource_sharing

##CORS support in Spring Framework

> ENGINEERING   SÉBASTIEN DELEUZE   JUNE 08, 2015   33 COMMENTS

For security reasons, browsers prohibit AJAX calls to resources residing outside the current origin. For example, as you’re checking your bank account in one tab, you could have the evil.com website in another tab. The scripts from evil.com shouldn’t be able to make AJAX requests to your bank API (withdrawing money from your account!) using your credentials.

Cross-origin resource sharing (CORS) is a W3C specification implemented by most browsers that allows you to specify in a flexible way what kind of cross domain requests are authorized, instead of using some less secured and less powerful hacks like IFrame or JSONP.

Spring Framework 4.2 GA provides first class support for CORS out-of-the-box, giving you an easier and more powerful way to configure it than typical filter based solutions.

Spring MVC provides high-level configuration facilities, described bellow.

##Controller method CORS configuration

You can add to your @RequestMapping annotated handler method a @CrossOrigin annotation in order to enable CORS on it (by default @CrossOrigin allows all origins and the HTTP methods specified in the @RequestMapping annotation):

```java

@RestController
@RequestMapping("/account")
public class AccountController {

	@CrossOrigin
	@RequestMapping("/{id}")
	public Account retrieve(@PathVariable Long id) {
		// ...
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void remove(@PathVariable Long id) {
		// ...
	}
}
```

It is also possible to enable CORS for the whole controller:

```java

@CrossOrigin(origins = "http://domain2.com", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

	@RequestMapping("/{id}")
	public Account retrieve(@PathVariable Long id) {
		// ...
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void remove(@PathVariable Long id) {
		// ...
	}
}

```
In this example CORS support is enabled for both retrieve() and remove() handler methods, and you can also see how you can customize the CORS configuration using @CrossOrigin attributes.

You can even use both controller and method level CORS configurations, Spring will then combine both annotation attributes to create a merged CORS configuration.

```java

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

	@CrossOrigin(origins = "http://domain2.com")
	@RequestMapping("/{id}")
	public Account retrieve(@PathVariable Long id) {
		// ...
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void remove(@PathVariable Long id) {
		// ...
	}
}

```
## Global CORS configuration

In addition to fine-grained, annotation-based configuration you’ll probably want to define some global CORS configuration as well. This is similar to using filters but can be declared withing Spring MVC and combined with fine-grained @CrossOrigin configuration. By default all origins and GET, HEAD and POST methods are allowed.

JavaConfig

Enabling CORS for the whole application is as simple as:

```java

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}

```
You can easily change any properties, as well as only apply this CORS configuration to a specific path pattern:

```java

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("http://domain2.com")
			.allowedMethods("PUT", "DELETE")
			.allowedHeaders("header1", "header2", "header3")
			.exposedHeaders("header1", "header2")
			.allowCredentials(false).maxAge(3600);
	}
}

```
> source: https://spring.io/guides/gs/rest-service-cors/
