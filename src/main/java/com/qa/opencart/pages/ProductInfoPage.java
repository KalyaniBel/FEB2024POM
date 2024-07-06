package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImagesCount = By.cssSelector("div#content a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String,String> productMap;
	

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.doGetText(productHeader);
		System.out.println("Product Header =====" + header);
		return header;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElemenetsLocated(productImagesCount, TimeUtil.DEFAULT_MEDIUM_TIME)
				.size();
		System.out.println("Total Images Of Product is ========" + imagesCount);
		return imagesCount;
	}
	public ShoppingCartInfoPage productAddToCart() {
		//jsUtil.scrollPageDown("1700");
		By productQuantity=By.cssSelector("div#product input#input-quantity");
		eleUtil.doSendKeys(productQuantity,"3",TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doClick(By.cssSelector("#product button#button-cart"));
		return new ShoppingCartInfoPage(driver);
	}
	private void getProductMetaData(){
		List<WebElement>metaList=eleUtil.getElements(productMetaData);
		for(WebElement e:metaList)
		{
			String metaData=e.getText();
			String meta[]=metaData.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productMap.put(metaKey,metaValue);
		}
	}
	private void getProductPriceData() {
		List<WebElement> priceList=eleUtil.getElements(productPriceMetaData);
		String productPrice=priceList.get(0).getText();
		String exTaxPrice=priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
	}
	public Map<String,String> getProductInfoMap() {
		productMap = new HashMap<String,String>();
		productMap.put("productname", getProductHeader());
		productMap.put("productImageCount",String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
}
