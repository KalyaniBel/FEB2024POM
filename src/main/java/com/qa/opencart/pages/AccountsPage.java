package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public String getAccPageTitle() {
//		String title = driver.getTitle();
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Account Page Title  :" + title);
		return title;
	}

	public String getAccPageURL() {
		//String url = driver.getCurrentUrl();
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_FRACTION_URL,TimeUtil.DEFAULT_TIME);

		System.out.println("Account Page URL :" + url);
		return url;
	}

	public boolean isLogOutLinkExist() {
//		return driver.findElement(logoutLink).isDisplayed();
		return eleUtil.doIsDisplayed(logoutLink);
	}

	public List<String> getAccPageHeaders() {
		//List<WebElement> headersList = driver.findElements(headers);
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElemenetsLocated(headers,TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}

	public boolean isSearchExist() {
		//return driver.findElement(search).isDisplayed();
		return eleUtil.doIsDisplayed(search);
	}

	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("Searching:  " + searchKey);
		if (isSearchExist()) {
//			driver.findElement(search).sendKeys(searchKey);
//			driver.findElement(searchIcon).click();
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		} else {
			System.out.println("Search Field Is Not Available");
			return null;
		}
		
	}
}
