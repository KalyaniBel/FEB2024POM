package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100:Design Opencart application with Shopping Workflow")
@Story("US101: Design logIn page for OpenCart application")
public class LoginPageTest extends BaseTest{
	@Description("Checking LogIn Page TItle")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	@Feature("LogIn Page Title Feature")
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND );
	}
	@Description("Checking LogIn Page Url")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	@Feature("Login Page URL Feature Test")
    public void loginPageURLTest() {
    	String actURL = loginPage.getLoginPageURL();
    	Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
    }
	@Description("Checking whether forgot password link is there or not")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	@Feature("ForgotPassword Link Exist Feature")
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotPwdLinkExist(),AppError.ELEMENT_NOT_FOUND);
	}
	@Description("Verifying whether user credentials are valid or not")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	@Feature("Login Test Feature")
	public void loginTest() {
	//accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	accPage=loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(),AppConstants.ACCOUNTS_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
}
