package com.apiautomation.Utility;

import org.hamcrest.Matchers;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Assertions {
	
	static ValidatableResponse validatableResponse;
	
	public static void checkStatusCode(Response response, int statusCode) {
		validatableResponse = response.then();
		validatableResponse.statusCode(200);
	}
	
	public static void checkResponseBody(Response response, String matcherkey, String matcherValue) {
		validatableResponse = response.then();
		if(matcherkey.equalsIgnoreCase("checknotnull")) {
			validatableResponse.body(matcherkey, Matchers.notNullValue());
		}
		else if(matcherkey.equalsIgnoreCase("error")) {
			validatableResponse.body(matcherkey, Matchers.containsString(matcherValue));
		}
	}
}
