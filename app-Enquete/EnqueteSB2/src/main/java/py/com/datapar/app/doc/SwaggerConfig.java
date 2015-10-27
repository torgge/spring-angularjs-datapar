package py.com.datapar.app.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

//@Configuration
//@EnableSwagger
public class SwaggerConfig {
	
	//private SpringSwaggerConfig springSwaggerConfig;

	//@Autowired
	//public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
//		this.springSwaggerConfig = springSwaggerConfig;
	//}

	//@Bean
	// Don't forget the @Bean annotation
//	public SwaggerSpringMvcPlugin customImplementation() {
	//	//return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns("/saurzcode/.*");
	//}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Enquete API", "API do app de Enquete", "Enquete API terms of service",
				"integraldominio@gmail.com", "IDomine API Licence Type", "IDomine API License URL");
		return apiInfo;
	}
	
}