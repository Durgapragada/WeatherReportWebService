package com.jsp.WeatherReportWebService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.WeatherReportWebService.DTO.Weather;
import com.jsp.WeatherReportWebService.Repository.WeatherRepository;

@Service
public class WeatherService {
	
	@Autowired
	WeatherRepository repository;
	
	//to insert an object into DB
	public Weather insertWeather(Weather weather)
	{
		return repository.save(weather);
	}
	
	//to search or fetch an object from DB based on PHK i.e weatherid
	public Weather getWeatherById(int id) {
		Optional <Weather> opt = repository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			return null;
		}	
	}
	
	//to delete an object from DB using ID
	public String deleteWeather(int id) {
		Weather w = getWeatherById(id);
		if(w!=null) {
			repository.delete(w);
			//repository.deleteById(id);
			//repository.deleteAll();
			return "Weather report deleted successfully";
		}
		else {
			return "Weather Report does not exist";
		}
	}
	
	public String updateWeather(int id, String temp,String condt) {
	 Weather w =getWeatherById(id);
	 if(w != null) {
		 w.setConditions(condt);
		 w.setTemperature(temp);
		 repository.save(w);
		 return "Weather Reported is Updated Successfully";	 
	 }
	 else {
		 return "Weather Report does not exist";
	 }
	}
	
	public List<Weather> getAllWeather(){
		return repository.findAll();
	}

}
