package com.testautomation.StepDef;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.junit.After;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.Listeners.ExtentReportListener;
import com.testautomation.PageObjects.SauceDemoAddToCart;
import com.testautomation.PageObjects.SauceDemoLoginPage;
import com.testautomation.PageObjects.SauceDemoLogout;
import com.testautomation.Utility.BrowserUtility;
import com.testautomation.Utility.ExcelHandler;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SauceDemoStepDef extends ExtentReportListener {

	PropertiesFileReader obj= new PropertiesFileReader();
	private WebDriver driver;
	String testDataFilepath;
	String testDataSheetPath;
	
	
	public SauceDemoStepDef(TestHooks hooks) throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = TestHooks.getDriver();
		
		Properties properties=obj.getProperty();
		this.testDataFilepath = properties.getProperty("testdatafilepath");
		this.testDataSheetPath = properties.getProperty("sheetname");
		System.out.println("Data file path "+testDataFilepath);
	}
	
	@Given("I verify the user is in correct website")
	public void verify_user_is_in_correct_page() throws Throwable
	{
		ExtentTest logInfo=null;
		try {
			test = extent.createTest(Feature.class, "Sauce Demo End To End Testing");							
			test=test.createNode(Scenario.class, "Add Items to cart in saucedemo website");	
			
			logInfo=test.createNode(new GherkinKeyword("Given"), "verify_user_is_in_correct_page");
			if(driver.getTitle() == "Swag Labs") {
				logInfo.pass("User is in correct page");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));	
			}
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
	
	@SuppressWarnings("static-access")
	@When("I login with standard user credentails")
	public void login_standard_user_credentails() throws Throwable
	{
		ExtentTest logInfo=null;
		try {
							
			logInfo=test.createNode(new GherkinKeyword("When"), "login_standard_user_credentails");		
			
			SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(driver);
			ExcelHandler excelHandler = new ExcelHandler();
			
			Map<String, String> testDataMap = excelHandler.getTestDataInMap(testDataFilepath, testDataSheetPath, "TestCase_001");
				
			String username = testDataMap.get("Username");
			String password = testDataMap.get("Password");
			
			sauceDemoLoginPage.fFillUsernameAndPassword(username, password);
			sauceDemoLoginPage.fClickLoginButton();
						
			logInfo.pass("User Login Completed");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}		
	}
	
	@Then("I verify login is successfull")
	public void verify_successfull_login() {
		ExtentTest logInfo=null;
		try {
							
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_successfull_login");		
			
			SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage(driver);
			boolean checkLoginSuccess = sauceDemoLoginPage.fVerifySuccessfullLogin();
			if(checkLoginSuccess == true) {
				logInfo.pass("User Login SUccessfull");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			else {
				logInfo.fail("User Login Failed");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
						
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}	
			
	}
	
	
	@SuppressWarnings("static-access")
	@When("I add multiple items to cart")
	public void add_mutiple_cart_items_to_cart() {
		ExtentTest logInfo=null;
		try {
							
			logInfo=test.createNode(new GherkinKeyword("When"), "add_mutiple_cart_items_to_cart");	
			
			SauceDemoAddToCart sauceDemoAddToCart = new SauceDemoAddToCart(driver);
			ExcelHandler excelHandler = new ExcelHandler();
			
			Map<String, String> testDataMap = excelHandler.getTestDataInMap(testDataFilepath, testDataSheetPath, "TestCase_001");
			
			String itemName1 = testDataMap.get("CartItem1");
			String itemName2 = testDataMap.get("CartItem2");
			
			sauceDemoAddToCart.fAddItemToCart(itemName1);
			sauceDemoAddToCart.fAddItemToCart(itemName2);
			
			sauceDemoAddToCart.fClickAddToCart();
			
			logInfo.pass("Items added to cart");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
						
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}	
	}
	
	
	@Then("I verify the items are added to cart")
	public void verify_items_are_added_to_cart() {
		ExtentTest logInfo=null;
		try {
							
			logInfo=test.createNode(new GherkinKeyword("Then"), "verify_items_are_added_to_cart");	
			
			SauceDemoAddToCart sauceDemoAddToCart = new SauceDemoAddToCart(driver);
			ExcelHandler excelHandler = new ExcelHandler();
			
			Map<String, String> testDataMap = excelHandler.getTestDataInMap(testDataFilepath, testDataSheetPath, "TestCase_001");
			
			String itemName1 = testDataMap.get("CartItem1");
			String itemName2 = testDataMap.get("CartItem2");
			
			Boolean checkFlag1 = sauceDemoAddToCart.fCheckItemAvailableInCart(itemName1);
			Boolean checkFlag2 = sauceDemoAddToCart.fCheckItemAvailableInCart(itemName1);
			
			if(checkFlag1 == true && checkFlag2 == true) {
				logInfo.pass("Items verified successfully");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			else {
				logInfo.fail("Items not verified successfully");
				logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			}
			

			sauceDemoAddToCart.fRemoveItemsForRegression();
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}	
	}
	
	@SuppressWarnings("static-access")
	@Then("I logout of the application")
	public void logout_application() {
		ExtentTest logInfo=null;
		try {
							
			logInfo=test.createNode(new GherkinKeyword("Then"), "logout_application");	
			
			SauceDemoLogout sauceDemoLogout = new SauceDemoLogout(driver);
			sauceDemoLogout.fLogoutFromApplication();
			
			logInfo.pass("Logout Successfull");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			
			
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",driver,logInfo,e);			
		}	
	}
	
}
