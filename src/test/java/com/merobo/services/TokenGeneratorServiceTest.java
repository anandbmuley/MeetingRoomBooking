package com.merobo.services;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class TokenGeneratorServiceTest {

	private TokenGeneratorService tokenGeneratorService;

	@BeforeClass
	public void setUp() {
		tokenGeneratorService = new TokenGeneratorService();
	}

	@Test
	public void ShouldGenerateTokenInThreeSteps() {
		// GIVEN
		String key = "Dark";
		String value = "Chocolate";

		// WHEN
		Boolean actual = tokenGeneratorService.tokenize(key, value);

		// THEN
		Assert.assertTrue(actual);
	}
	
}
