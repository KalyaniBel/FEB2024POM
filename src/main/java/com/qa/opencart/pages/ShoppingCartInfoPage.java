package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ShoppingCartInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public ShoppingCartInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}
	public By itemAdded = By.xpath("//div[@id=\"product-product\"]/div[1]");
	public boolean IsSuccessMessageDisplayed() {
		//return eleUtil.doIsDisplayed(itemAdded);
		String message;
		boolean flag;
		WebElement e = eleUtil.waitForElementVisible(itemAdded, TimeUtil.DEFAULT_MEDIUM_TIME);
		if(!(e.getText().isEmpty()))
		{
			message = e.getText();
			System.out.println(message);
			flag=true;
		}  
		else 
		{
			message = "no element displayed";
			System.out.println(message);
			flag=false;
		}
		return flag;
	}
	
	public  ShoppingCartCheckOutPage clickOnCart() {
		eleUtil.clickWhenReady(By.cssSelector("div#cart span#cart-total"),TimeUtil.DEFAULT_TIME);
	eleUtil.doClick(By.cssSelector("p.text-right a"));
	return new ShoppingCartCheckOutPage(driver);
	}
	

}
