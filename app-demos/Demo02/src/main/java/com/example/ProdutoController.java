package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository p;
	
	@RequestMapping("api/produtos")
	public Iterable<Produto> lista(){
		return p.findAll();
		
	}
	
}
