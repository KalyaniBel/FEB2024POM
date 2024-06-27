package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCartCheckOutPage;
import com.qa.opencart.pages.ShoppingCartInfoPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected ShoppingCartInfoPage shoppingCartInfoPage;
	protected ShoppingCartCheckOutPage shoppingCartCheckOutPage;
	protected SoftAssert softAssert;
    
	
	@Step("Setup for the test, initalizing Browser :{0}")
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		//driver = df.initDriver("chrome");
		loginPage = new LoginPage(driver);
		softAssert=new SoftAssert();
	}

	
//	@Step("close browser")
//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}
}
