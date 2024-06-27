package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;

public class ShoppingCartCheckOutPageTest extends BaseTest {
	@Test
	public void ItemsAddedSuccessfully(String searchKey,String productName) {
		searchResultsPage=accPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		shoppingCartInfoPage=productInfoPage.productAddToCart();
		Assert.assertTrue(shoppingCartInfoPage.IsSuccessMessageDisplayed(),AppError.ADDTOCART_FAILED);
	}

}
