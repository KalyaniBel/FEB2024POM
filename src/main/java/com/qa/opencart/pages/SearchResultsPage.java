package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private By searchResult = By.cssSelector("div.product-thumb");
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
   public int getSearchResultsCount(){
	 List<WebElement>resultsList =  eleUtil.waitForVisibilityOfElemenetsLocated(searchResult,TimeUtil.DEFAULT_MEDIUM_TIME);
   int resultsCount = resultsList.size();
   System.out.println("Search Results Count ---"+resultsCount);
   return resultsCount;
   }
   public ProductInfoPage selectProduct(String productName) {
	   eleUtil.doClick(By.linkText(productName),TimeUtil.DEFAULT_MEDIUM_TIME);
	   return new ProductInfoPage(driver);
   }

}
