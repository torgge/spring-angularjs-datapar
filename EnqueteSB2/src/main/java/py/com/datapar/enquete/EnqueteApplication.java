package py.com.datapar.enquete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import py.com.datapar.enquete.security.JwtFilter;

@SpringBootApplication
public class EnqueteApplication {
	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/XXX/*");
        return registrationBean;
	}

    public static void main(String[] args) {
    	
    	ConfigurableApplicationContext app =
    			SpringApplication.run(EnqueteApplication.class, args);

    	//ParticipanteResource p = app.getBean(ParticipanteResource.class);
    	//p.gerar100(10);

    	//PerguntaResource perg = app.getBean(PerguntaResource.class);
    	//perg.gerar100(10);

    	//EnqueteResource e = app.getBean(EnqueteResource.class);
    	//e.gerar100(2);
    	
       
    }
}
