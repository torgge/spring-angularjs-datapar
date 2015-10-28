package com.idomine.masterchief.apidoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(springSwaggerConfig).apiInfo(
                apiInfo());
    }

    private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"MasterChico API", "API do app de MasterChico", "MasterChico API terms of service",
				"integraldominio@gmail.com", "IDomine API Licence Type", "IDomine API License URL");
		return apiInfo;
  }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}