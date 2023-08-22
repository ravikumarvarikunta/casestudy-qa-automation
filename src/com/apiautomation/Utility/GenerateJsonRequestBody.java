package com.apiautomation.Utility;

public class GenerateJsonRequestBody {

	public static String fGenerateRequestBodyForLoginAPI(String username, String password) {
		
		String jsonString = "{\n"
        		+ "    \"email\": \""+username+"\",\n"
        		+ "    \"password\": \""+password+"\"\n"
        		+ "}";
		
		return jsonString;
	}
}
