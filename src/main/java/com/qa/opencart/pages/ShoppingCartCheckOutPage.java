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

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class ShoppingCartCheckOutPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productName = By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[2]");
	private By productDetails = By.xpath("//div[@id='content']//tbody//td[@class='text-left']");
	//private By productQuantity=By.xpath("//div[@id='content']//tbody//input");
	//private By productQuantityTo=By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//input");
	private By productUpdate=By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//button[@type='submit']");
	public By productQuantity=By.xpath("//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//input");
	public ShoppingCartCheckOutPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
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
		
	 //   System.out.println("Product Quantity List Is  ----"+prodQuantity);
	//	List<String> eleTextList = new ArrayList<String>();
//		int eleCount = eleUtil.getElementsCount(productDetails);
//		System.out.println("Elements Found Are  "+eleCount);
//		List<WebElement>prodDetailsWebElements = eleUtil.getElements(productDetails);
//		for(WebElement e : prodDetailsWebElements)
//		{
//			if(e.getTagName()=="input")
//				System.out.println(e.getAttribute("value"));
//			else
//			{
//			System.out.println(e.getTagName());
//			if(e.getText().isBlank()) {
//			
//				System.out.println("In If block");
//				System.out.println(e.getAttribute("innerHTML"));
//			   
//			}
//			else
//		      System.out.println(e.getText());
//			}
//		
//		}
//		String str1 = null;
//		String val = null;
//		int count = 0;
//		for (WebElement e : eleList) {
//			String str = e.getText();
//			System.out.println(str);
//			if (!(str.contains("Product"))) {
//				count++;
//				str1 = str;
//				System.out.println("str1 Val---"+str1);
//			} else if (str.startsWith("$")) {
//				count++;
//				val =str ;
//			} else {
//				System.out.println("In Product Line");
//				if (count == 2) {
//					prodNameQuantMap.put(str1, val);
//					count = 0;
//					str1 = null;
//					val = null;
//				}
//			}
//
//		}
//
//		System.out.println("Product Name Quantity HashMap" + prodNameQuantMap);
//		return prodNameQuantMap;
	}

	public void editShoppingCart(int i) {
//		String productToChange ="MacBook Air ***"; 
//		List<WebElement> prodName = eleUtil.getElements(productName);
//		List<WebElement> prodQuantityElements = eleUtil.getElements(productQuantity);
//		Map<WebElement, WebElement> prodNameQuantMapElements = new HashMap();
//		for(int i=0;i<prodName.size();i++)
//			prodNameQuantMapElements.put(prodName.get(i),prodQuantityElements.get(i));
//		for(int i=0;i<prodName.size();i++)
//		{
//			if((prodNameQuantMapElements.containsKey(productToChange)))
//			{      
//				   prodNameQuantMapElements.get(productToChange).sendKeys("10");
//                   
//			}
	//}
		String qLocator="(//td[text()='Product Name']/ancestor::thead/following-sibling::tbody/tr/td[4]//input)"+ "["+String.valueOf(i)+"]";
		
		 By productQuantityTo=By.xpath(qLocator);
		eleUtil.doSendKeys(productQuantityTo,"2");
		eleUtil.doClick(productUpdate);
		
}
	public void deleteShoppingCartItem(int i) {
		String deletebutton = "(//div[@id='content']//tbody//td//span[@class='input-group-btn']/button[@type='button'])"+"["+String.valueOf(i)+"]";
	   By productQuantityDeleteLoc = By.xpath(deletebutton);
	   eleUtil.doClick(productQuantityDeleteLoc);
	}
	public int shoppingCartProductRowCount(){
		By tableLocator = By.xpath("//td[text()='Product Name']//ancestor::table//tr");
		int rowcount=eleUtil.getElementsCount(tableLocator);
		return rowcount;
	}
}
