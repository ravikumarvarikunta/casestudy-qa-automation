package com.apiautomation.Tests;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.apiautomation.Utility.Assertions;
import com.apiautomation.Utility.GenerateJsonRequestBody;
import com.apiautomation.Utility.GetResources;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.StepDef.TestHooks;
import com.testautomation.Utility.ExcelHandler;
import com.testautomation.Utility.PropertiesFileReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CaseStudyAPIAutomation extends ExtentReportListener {
	RequestSpecification requestSpecification;
	Response response;
	ValidatableResponse validatableResponse;

	PropertiesFileReader obj= new PropertiesFileReader();
	String testDataFilepath;
	String testDataSheetPath;

	WebDriver driver;


	public CaseStudyAPIAutomation() throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = TestHooks.getDriver();
		Properties properties=obj.getProperty();
		this.testDataFilepath = properties.getProperty("testdatafilepath");
	}

	@SuppressWarnings("static-access")
	@Test
	public void CaseStudyAPIAutomationPositiveScenario() throws Exception {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest("API - Case Study API Automation Positive Scenario");							
			test=test.createNode("Case Study API Automation Positive Scenario - Start");	

			ExcelHandler excelHandler = new ExcelHandler();

			Map<String, String> testDataMap = excelHandler.getTestDataInMap(testDataFilepath, "TestData02", "TestCase_001");

			String username = testDataMap.get("Username");
			String password = testDataMap.get("Password");    	

			RequestSpecification request= RestAssured.given();

			request.contentType(ContentType.JSON);


			request.baseUri(GetResources.fGetResourceURLs());
			request.body(GenerateJsonRequestBody.fGenerateRequestBodyForLoginAPI(username, password));

			Response response = request.post();

			System.out.println(response.asPrettyString());

			Assertions.checkStatusCode(response, 200);
			Assertions.checkResponseBody(response, "token", "checknotnull");
			test=test.createNode("API Endpoint : "+GetResources.fGetResourceURLs());
			test=test.createNode("API Request Body : "+GenerateJsonRequestBody.fGenerateRequestBodyForLoginAPI(username, password));
			test=test.createNode("API Respone Body : "+response.asPrettyString());
			test=test.createNode("Case Study API Automation Positive Scenario - Success");	
		} catch (AssertionError | Exception e) {
			test=test.createNode("Case Study API Automation Positive Scenario - Failed");	 
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}

	@SuppressWarnings("static-access")
	@Test
	public void CaseStudyAPIAutomationNegativeScenario() throws Exception {
		ExtentTest logInfo=null;
		try {
			test = extent.createTest("API - Case Study API Automation Negative Scenario");							
			test=test.createNode("Case Study API Automation Negative Scenario");	

			ExcelHandler excelHandler = new ExcelHandler();

			Map<String, String> testDataMap = excelHandler.getTestDataInMap(testDataFilepath, "TestData03", "TestCase_001");

			String username = testDataMap.get("Username");
			String password = testDataMap.get("Password");    	

			RequestSpecification request= RestAssured.given();

			request.contentType(ContentType.JSON);


			request.baseUri(GetResources.fGetResourceURLs());
			request.body(GenerateJsonRequestBody.fGenerateRequestBodyForLoginAPI(username, password));

			Response response = request.post();

			System.out.println(response.asPrettyString());
			
			Assertions.checkResponseBody(response, "error", "user not found");
			test=test.createNode("API Endpoint : "+GetResources.fGetResourceURLs());
			test=test.createNode("API Request Body : "+GenerateJsonRequestBody.fGenerateRequestBodyForLoginAPI(username, password));
			test=test.createNode("API Respone Body : "+response.asPrettyString());
			test=test.createNode("Case Study API Automation Negative Scenario - Success");
		} catch (AssertionError | Exception e) {
			test=test.createNode("Case Study API Automation Negative Scenario - Failed");
			testStepHandle("FAIL",driver,logInfo,e);			
		}
	}


}