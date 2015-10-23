package py.com.datapar.master.resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.datapar.master.model.email.Mensagem;

@RestController("api")
public class EmailResource {

	
	@RequestMapping("send")
	public void sendEmail(@RequestBody final Mensagem message){
		
	}
	
	
	
}
