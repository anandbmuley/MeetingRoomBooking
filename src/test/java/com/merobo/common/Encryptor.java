package com.merobo.common;

import sun.misc.BASE64Encoder;

public class Encryptor {

	public static void main(String[] args) {
		String beforEncoding = "PassItOn";
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String encoded = base64Encoder.encode(beforEncoding.getBytes());
		System.out.println("ENCODED :: > " + encoded);
	}

}
