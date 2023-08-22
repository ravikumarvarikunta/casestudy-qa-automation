package com.testautomation.TestRunner;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.*;

@CucumberOptions (
        features = "./features/"
        ,glue = {"com.testautomation.StepDef"}
        ,tags = {"@TestCase1"}
        ,monochrome = true)

public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
    
    public static WebDriver driver;
    
    @BeforeSuite
	public void beforeSuite() throws InterruptedException, IOException {
		System.out.println("Before Suite -- Initiated");
		PropertiesFileReader obj= new PropertiesFileReader();
		Properties properties=obj.getProperty(); 
		driver=BrowserUtility.OpenBrowser(driver, properties.getProperty("browser.name"), properties.getProperty("browser.baseURL"));
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		System.out.println("After Suite -- Completed");
		driver.quit();
	}
	
	public static WebDriver returnDriver() {
		return driver;
	}
    
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {    	
    	System.out.println("This is a before class method");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")    
    public void feature(PickleEventWrapper eventwrapper,CucumberFeatureWrapper cucumberFeature) throws Throwable {
    	//testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    	testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());
    }
    
    @DataProvider//(parallel=true)
    public Object[][] features() {
       // return testNGCucumberRunner.provideFeatures();    	
    	 return testNGCucumberRunner.provideScenarios();
    }
    
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {    	
        testNGCucumberRunner.finish();        
    }
}