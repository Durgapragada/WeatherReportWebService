package com.jsp.WeatherReportWebService.DTO;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Weather {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int weatherid;
	private String city;
	private String temperature;
	private String conditions; //sunny , cloudy , rainy
	private LocalDate date;//yyyy-mm-dd
	public int getWeatherid() {
		return weatherid;
	}
	public void setWeatherid(int weatherid) {
		this.weatherid = weatherid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	

}
