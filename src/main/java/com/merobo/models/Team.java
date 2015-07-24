package com.merobo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {

	@JsonProperty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + "]";
	}

}
