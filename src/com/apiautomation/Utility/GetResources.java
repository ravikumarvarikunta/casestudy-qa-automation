package com.apiautomation.Utility;

import java.io.IOException;
import java.util.Properties;

import com.testautomation.Utility.PropertiesFileReader;

public class GetResources {

	public static String fGetResourceURLs() throws IOException {
		PropertiesFileReader obj= new PropertiesFileReader();
		Properties properties=obj.getProperty();
		String apiBaseURL = properties.getProperty("apibaseurl");
		String apiEndpoint = properties.getProperty("endpoint");
		String apiURL = apiBaseURL+""+apiEndpoint;
		return apiURL;
	}
}
