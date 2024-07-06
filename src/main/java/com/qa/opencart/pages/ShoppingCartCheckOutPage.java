package com.qa.opencart.pages;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;
import com.qa.opencart.utils.TimeUtil;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class ShoppingCartCheckOutPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	private By productName = By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[2]");
	private By productDetails = By.xpath("//div[@id='content']//tbody//td[@class='text-left']");
	//private By productQuantity=By.xpath("//div[@id='content']//tbody//input");
	//private By productQuantityTo=By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//input");
	private By productUpdate=By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//button[@type='submit']");
	public By productQuantity=By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//input");
	public ShoppingCartCheckOutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public void getProductDetails() {
		Map<String, String> prodNameQuantMap = new HashMap<String, String>();
		List<String> prodNameList=new ArrayList<String>();
		List<String> prodDetails = new ArrayList<String>();
		List<String> prodQuantityList = new ArrayList<String>();
		List<WebElement> prodName = eleUtil.getElements(productName);
		for(WebElement e:prodName) {
		     System.out.println(e.getText());
		     prodNameList.add(e.getText());
		}
		List<WebElement> prodQuantityElements = eleUtil.getElements(productQuantity);
		for(WebElement e:prodQuantityElements)
		{
		     System.out.println(e.getAttribute("value"));
		     prodQuantityList.add(e.getAttribute("value"));
		}
		for(int i=0;i<prodNameList.size();i++)
			prodNameQuantMap.put(prodNameList.get(i),prodQuantityList.get(i));
		System.out.println("ProductNameQuantity HashMap Contents*************"+prodNameQuantMap);
	
	}

	public void editShoppingCart(int i) {

		String qLocator="(//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//input)"+ "["+String.valueOf(i)+"]";
	jsUtil.scrollPageDown("1500");
		 By productQuantityTo=By.xpath(qLocator);
		eleUtil.doSendKeys(productQuantityTo,"2");
		eleUtil.doClick(productUpdate);
		
}
	public String[] totalCost(int i) {
		String itemCostLoc="(//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[5])"+ "["+String.valueOf(i)+"]";
		String totalLoc="(//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[6])"+ "["+String.valueOf(i)+"]";
		String[] prodCost=new String[2];
  	jsUtil.scrollPageDown("1500");
	By itemCostLocator=By.xpath(itemCostLoc);
	String actualItemVal=eleUtil.waitForElementVisible(itemCostLocator,TimeUtil.DEFAULT_TIME).getText();
	System.out.println("actualItem Cost:  "+actualItemVal);
	 prodCost[0]=actualItemVal.replace('$', ' ' );
     System.out.println("Cost per Unit"+prodCost[0]);
	By totalLocator=By.xpath(totalLoc);
	String totalVal=eleUtil.getElement(totalLocator).getText();
	System.out.println("total Cost:  "+totalVal);
	prodCost[1]=totalVal.replace('$',' ');
	System.out.println("prodCost[0]:  "+prodCost[0]);
	System.out.println("prodCost[1]:  "+prodCost[1]);
	return prodCost;
	}
	public void deleteShoppingCartItem(int i) {
		String deletebutton = "(//div[@id='content']//tbody//td//span[@class='input-group-btn']/button[@type='button'])"+"["+String.valueOf(i)+"]";
    	jsUtil.scrollPageDown("1700");
		By productQuantityDeleteLoc = By.xpath(deletebutton);
	   eleUtil.doClick(productQuantityDeleteLoc);
	}
	public int shoppingCartProductRowCount(){
		By tableLocator = By.xpath("//td[text()='Product Name']//ancestor::table//tbody/tr");
		int rowcount=eleUtil.getElementsCount(tableLocator);
		return rowcount;
	}
}
