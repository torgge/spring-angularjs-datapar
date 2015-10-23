package py.com.datapar.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ApplicationJavaEmail {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationJavaEmail.class, args);
    }
    
    /*


		@Configuration
		@PropertySource("classpath:mail.properties")
		public class MailConfiguration {
		
		    @Value("${mail.protocol}")
		    private String protocol;
		    @Value("${mail.host}")
		    private String host;
		    @Value("${mail.port}")
		    private int port;
		    @Value("${mail.smtp.auth}")
		    private boolean auth;
		    @Value("${mail.smtp.starttls.enable}")
		    private boolean starttls;
		    @Value("${mail.from}")
		    private String from;
		    @Value("${mail.username}")
		    private String username;
		    @Value("${mail.password}")
		    private String password;
		
		    @Bean
		    public JavaMailSender javaMailSender() {
		        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		        Properties mailProperties = new Properties();
		        mailProperties.put("mail.smtp.auth", auth);
		        mailProperties.put("mail.smtp.starttls.enable", starttls);
		        mailSender.setJavaMailProperties(mailProperties);
		        mailSender.setHost(host);
		        mailSender.setPort(port);
		        mailSender.setProtocol(protocol);
		        mailSender.setUsername(username);
		        mailSender.setPassword(password);
		        return mailSender;
		    }
		}



     */
    
    
    
}