package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.CSVUtil;

import io.qameta.allure.Description;

public class ProductInfoPageTest extends BaseTest{
	@BeforeClass
	public void productInfoPageSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
				{"samsung","Samsung Galaxy Tab 10.1"},
				{"canon","Canon EOS 5D"}};
	}
	@DataProvider
	public Object[][] getProductImageData(){
		return new Object[][] {
			{"macbook","MacBook",5},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
				{"samsung","Samsung Galaxy Tab 10.1",7},
				{"canon","Canon EOS 5D",3}};
	}	
	
	@DataProvider
	public Object[][] getProductImageDataFromCSV(){
	return CSVUtil.csvData(AppConstants.PROD_INFO_PAGE_CSV);
	}
	@Description("Verifying the search field by searching specific item and test the displayed products")
	@Test(dataProvider="getProductData")
	public void productHeaderTest(String searchKey,String productName) {
		searchResultsPage=accPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(),productName, AppError.HEADER_NOT_FOUND);
	}
	@Description("Verifying the product images count in the selected product")
	@Test(dataProvider="getProductImageDataFromCSV")
	public void productImageCountTest(String searchKey,String productName, String imagesCount) {
		searchResultsPage=accPage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(),Integer.parseInt(imagesCount),AppError.IMAGES_COUNT_MISMATCH);
	}
	@Description("Verifying the product information of the selected product")
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap = productInfoPage.getProductInfoMap();
		System.out.println("========Product Information==========");
		System.out.println(productInfoMap);
		softAssert.assertEquals(productInfoMap.get("productname"),"MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"),"800");
		softAssert.assertEquals(productInfoMap.get("Availability"),"In Stock");
		softAssert.assertEquals(productInfoMap.get("productPrice"),"$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"),"$2,000.00");
		softAssert.assertAll();
		System.out.println("Test Is Done!!!!!!");
	}

}
