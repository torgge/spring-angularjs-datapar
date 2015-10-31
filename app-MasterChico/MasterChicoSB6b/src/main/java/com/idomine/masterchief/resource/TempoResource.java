package com.idomine.masterchief.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.idomine.masterchief.weather.Tempo;
import com.idomine.masterchief.weather.model.OpenWeatherMap;

@RestController
@RequestMapping("/api")
public class TempoResource {

	private final String URL_TEMPO = "http://api.openweathermap.org/data/2.5/weather?q=";
	//private final String URL_IMAGE = "http://openweathermap.org/img/w/";
	private final String API_MODE  = "&mode=xml";
	private final String API_KEY   = "&appid=bd82977b86bf27fb59a04b61b657fb6f";
	private final String API_UNIT  = "&units=metric";

	@RequestMapping("tempo/{cidade}")
	public Tempo tempo( @PathVariable  String  cidade) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<OpenWeatherMap> tempo = 
				restTemplate.getForEntity(URL_TEMPO + cidade + API_MODE + API_UNIT+ API_KEY,
				OpenWeatherMap.class);

		return new Tempo(
				tempo.getBody().getCity().getName(),
				tempo.getBody().getTemperature().getValue(),
				tempo.getBody().getWeather().getIcon());
	}

}
