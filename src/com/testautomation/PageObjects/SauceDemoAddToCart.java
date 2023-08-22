package com.testautomation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoAddToCart {


	WebDriver driver;

	public SauceDemoAddToCart(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void fAddItemToCart(String itemName) {
		WebElement addCartItem = driver.findElement(By.xpath("(//div[text()='"+itemName+"']//parent::a//parent::div//parent::div//parent::div)[1]//div[@class=\"pricebar\"]/button[text()='Add to cart']"));
		
		if(addCartItem.isDisplayed() == true) {
			addCartItem.click();
		}
	}
	
	@FindBy(xpath="//div[@id=\"shopping_cart_container\"]")
	public WebElement shoppingcartbutton;
	
	public void fClickAddToCart() {
		shoppingcartbutton.click();
	}
	
	public boolean fCheckItemAvailableInCart(String itemName) {
		
		Boolean checkItemFlag = driver.findElement(By.xpath("//div[text()='"+itemName+"']")).isDisplayed();
		return checkItemFlag;
	}
	
	public void fRemoveItemsForRegression() {
		List<WebElement> removeButtonList = driver.findElements(By.xpath("//button[text()='Remove']"));
		
		for(WebElement removeButton : removeButtonList) {
			removeButton.click();
		}
		
	}
}
