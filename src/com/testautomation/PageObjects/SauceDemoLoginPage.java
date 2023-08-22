package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoLoginPage {
	
	WebDriver driver;
	
	public SauceDemoLoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id=\"user-name\"]")
	public WebElement usernameinput;
	
	@FindBy(xpath="//input[@id=\"password\"]")
	public WebElement passwordinput;
	
	@FindBy(xpath="//input[@id=\"login-button\"]")
	public WebElement loginbutton;
	
	@FindBy(xpath="//a[@class=\"shopping_cart_link\"]")
	public WebElement shoppingcartbutton;
	
	public void fFillUsernameAndPassword(String username, String password)
	{
		usernameinput.sendKeys(username);
		passwordinput.sendKeys(password);
	}
	
	public void fClickLoginButton() {
		loginbutton.click();
	}
	
	public boolean fVerifySuccessfullLogin() {
		if(shoppingcartbutton.isDisplayed() == true) {
			return true;
		}
		else {
			return false;
		}
	}
}
