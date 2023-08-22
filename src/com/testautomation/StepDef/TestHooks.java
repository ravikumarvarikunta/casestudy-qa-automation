package com.testautomation.StepDef;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.testautomation.TestRunner.TestRunner;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestHooks {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		driver = TestRunner.returnDriver();
		return driver;
	}
	
	@Before
	public void beforeScenario(Scenario scenario) {		
		System.out.println("Started execution for the scenario : " + scenario.getName());
	}
	
	@After
	public void AfterScenario(Scenario scenario) {
		System.out.println("Completed execution for the scenario :" + scenario.getName());
	}
	

}
