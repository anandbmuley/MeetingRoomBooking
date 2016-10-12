package com.merobo.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TokenGeneratorService {

	private String passcode = "TW9jaGE=";

	public String validate(String passcode) {
		String result = null;
		if (this.passcode.equals(passcode)) {
			result = UUID.randomUUID().toString();
		}
		return result;
	}

}
