package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamTo {

	@JsonProperty
	private String name;
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
