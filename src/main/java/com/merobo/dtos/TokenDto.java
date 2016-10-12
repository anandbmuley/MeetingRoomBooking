package com.merobo.dtos;

public class TokenDto {

	private boolean validated;
	private String key;

	public TokenDto(String key) {
		super();
		this.key = key;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "TokenDto [validated=" + validated + ", key=" + key + "]";
	}

}
