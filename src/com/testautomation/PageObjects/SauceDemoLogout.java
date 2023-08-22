package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoLogout {

	WebDriver driver;
	
	public SauceDemoLogout(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@id=\"react-burger-menu-btn\"]")
	public WebElement hamburgerbutton;
	
	@FindBy(xpath="//a[text()='Logout']")
	public WebElement logoutbutton;
	
	public void fLogoutFromApplication() {
		hamburgerbutton.click();
		logoutbutton.click();
	}
}
