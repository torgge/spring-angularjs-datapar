package com.idomine.appquizzer.resource.grafico;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idomine.appquizzer.model.grafico.GraficoJson;

@RestController
@RequestMapping("/api")
public class GraficosResource {

	
	@Autowired
	private EntityManager em;
	
	@RequestMapping("/graficoValorEstoque")
	@SuppressWarnings("unchecked")
	public GraficoJson graficoValorEstoque(){
		List<String> labels = new ArrayList<>();
		List<String> series = new ArrayList<>();
		List<BigDecimal> data = new ArrayList<>();
		GraficoJson dadosJson = new GraficoJson();
		labels.add("2015");
		
		List<Object[]> dadosObj = 
				em.createNativeQuery("***elabora a consulta aqui***").getResultList();
		
		for (Object[] d : dadosObj) {
			series.add( (String) "(" + d[0] + ") "+d[1]+ " R$ " + d[2] );
			data.add((BigDecimal) d[2]);
		}
		
		dadosJson.setLabels(labels);
		dadosJson.setSeries(series);
		dadosJson.setData(data);
		
		System.out.println(dadosJson);
		
		return  dadosJson;
	}
	
}