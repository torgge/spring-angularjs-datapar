package com.idomine.masterchief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.idomine.masterchief.model.Usuario;
import com.idomine.masterchief.model.UsuarioSituacao;
import com.idomine.masterchief.model.UsuarioTipo;
import com.idomine.masterchief.repository.UsuarioRepository;
import com.idomine.masterchief.resource.CategoriaResource;
import com.idomine.masterchief.resource.FornecedorResource;
import com.idomine.masterchief.resource.MercaderiaResource;
import com.idomine.masterchief.resource.PedidoItemResource;
import com.idomine.masterchief.resource.PedidoResource;
import com.idomine.masterchief.resource.UnidadeResource;
import com.idomine.materchief.security.JwtFilter;

@SpringBootApplication
public class MasterCheief6bApplication {

	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api2/*");
        return registrationBean;
	}

	
    public static void main(String[] args) {
     

    	ConfigurableApplicationContext app =
    			SpringApplication.run(MasterCheief6bApplication.class, args);

    	//pegar contexto e bean para execução de tarefas após iniciar...
    	CategoriaResource catgo = app.getBean(CategoriaResource.class);
    	catgo.gerar100(100);
    	
    	UnidadeResource unid = app.getBean(UnidadeResource.class);
    	unid.gerar100(100);
    	
    	MercaderiaResource merc = app.getBean(MercaderiaResource.class);
    	merc.gerar100(1000);
    	
    	FornecedorResource forn = app.getBean(FornecedorResource.class);
    	forn.gerar100(100);
    	
    	PedidoResource pedi = app.getBean(PedidoResource.class);
    	pedi.gerar100(100);

    	PedidoItemResource item = app.getBean(PedidoItemResource.class);
    	item.gerar100(100);
       
    	UsuarioRepository usu = app.getBean(UsuarioRepository.class);

    	usu.save(new Usuario("Lyndon","Lyndon","123",UsuarioSituacao.ATIVO,UsuarioTipo.ADMIN ));
    	usu.save(new Usuario("George","George","123",UsuarioSituacao.ATIVO,UsuarioTipo.USER ));
    	usu.save(new Usuario("Pradebon","Pradebon","123",UsuarioSituacao.ATIVO,UsuarioTipo.USER ));
    	usu.save(new Usuario("adm","adm","123",UsuarioSituacao.ATIVO,UsuarioTipo.ADMIN ));
    	
    }
}
