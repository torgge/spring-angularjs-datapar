package com.integraldominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.integraldominio.modelo.Pessoa;
import com.integraldominio.repository.PessoaRepository;

public class PessoaController {

	
	 @Autowired
	    private PessoaRepository repository;
	 
	    @RequestMapping("/pessoa")
	    List<Pessoa> characters() {
	        return repository.findAll();
	    }
	 
	    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    void delete(@PathVariable("id") Integer id) {
	        repository.delete(id);
	    }
	
}
 