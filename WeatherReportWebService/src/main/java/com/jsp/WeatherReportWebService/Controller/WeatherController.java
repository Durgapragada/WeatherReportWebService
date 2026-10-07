package com.jsp.WeatherReportWebService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.WeatherReportWebService.DTO.Weather;
import com.jsp.WeatherReportWebService.Service.WeatherService;

@RestController
public class WeatherController {
	
	@Autowired
	WeatherService service;
	
	@PostMapping("/weather")
	public Weather insertWeatherReport(@RequestBody Weather weather) {
		return service.insertWeather(weather);
		
	}
	
	@GetMapping("/weather")
	public Weather getWeatherReportById(@RequestParam int id) {
		return service.getWeatherById(id);
	}
	
	@DeleteMapping("/weather")
	public String deleteWeatherReport(@RequestParam int id) {
		return service.deleteWeather(id);
	}
	
	@PutMapping("/weather")
	public String updateWeatherReport(@RequestParam int id, @RequestParam String temp,@RequestParam String condt) {
		return service.updateWeather(id, temp, condt);
	}
	
	@GetMapping("/all")
	public List<Weather> getAllWeatherReports(){
		return service.getAllWeather();
	}
}
