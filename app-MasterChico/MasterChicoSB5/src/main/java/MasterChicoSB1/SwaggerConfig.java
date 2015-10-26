package MasterChicoSB1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

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