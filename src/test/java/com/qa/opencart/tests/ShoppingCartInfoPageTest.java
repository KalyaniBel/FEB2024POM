package com.qa.opencart.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.TimeUtil;

public class ShoppingCartInfoPageTest extends BaseTest {
	@BeforeClass
	public void ShoppingCartInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "macbook", "MacBook" }, {"macbook", "MacBook Air"}, { "iphone", "iPhone" } };
	}

	@Test(dataProvider = "getProductData",priority=1)
	public void ItemsAddedSuccessfully(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		shoppingCartInfoPage = productInfoPage.productAddToCart();
		Assert.assertTrue(shoppingCartInfoPage.IsSuccessMessageDisplayed(), AppError.ADDTOCART_FAILED);
	}

	@Test(priority=2)
	public void clickOnCartTest() {
		shoppingCartCheckOutPage = shoppingCartInfoPage.clickOnCart();

	}

	@Test(priority=3)
	public void editShoppingCartTest() {
			
		shoppingCartCheckOutPage.getProductDetails();
		shoppingCartCheckOutPage.editShoppingCart(1);
		shoppingCartCheckOutPage.getProductDetails();
		

	}
	@Test(priority=4)
	public void deleteShoppingCartTest() {
		int expectedcount = shoppingCartCheckOutPage.shoppingCartProductRowCount()-1;	
		System.out.println("expected count"+expectedcount);
		shoppingCartCheckOutPage.deleteShoppingCartItem(1);
		int actualcount=shoppingCartCheckOutPage.shoppingCartProductRowCount();
		System.out.println("Actual count" +actualcount);
		Assert.assertEquals(actualcount,expectedcount,AppError.RESULTS_COUNT_MISMATCHED);
		
	}
}
