package com.WhatFix.pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.WhatFix.util.BasePageObject;

public class WhatFixverify extends BasePageObject {
	WhatFixverify objLoginPage = null;
	WebDriverWait wait;
	int statusCode;
	public WhatFixverify(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;

	public static Logger logger = Logger.getLogger(WhatFixverify.class);


	/* Web elements */

	By login = By.id("login-btn");
	By user_email = By.id("login-email");
	By user_pwd = By.name("password");
	By login_btn = By.id("Login-Button");
	By InstallEditorLink=By.id("plus_flow");
	By error_text=By.xpath("//div[contains(text(),'invalid mail address/password')]");
	public boolean isloginPageDisplayed() throws Exception {
		try {
			flag = isElementPresent(login);
			System.out.println("Added one line");
			if (flag) {
				logger.info("Login Page is displayed");
			} else {
				logger.info("Login Page is not displayed");

			}
		} catch (Exception e) {
			throw new Exception("Login page is displayed::" + isloginPageDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	public void enterEmailAddr(String username) throws Exception {
		if (isElementPresent(login)) {
			setElement(login).click();
			waitForAnElement(user_email, 10);
			setElement(user_email).sendKeys(username);
			Assert.assertTrue(isElementPresent(user_email), "Email textbox is not displayed");

		}
	}

	public void enterPassword(String Password) throws Exception {
		if (isElementPresent(user_pwd)) {
			setElement(user_pwd).sendKeys(Password);
			Assert.assertTrue(isElementPresent(user_pwd), "Password textbox is not displayed");

		}

	}

	public void clickLogin() throws Exception {
		if (isElementPresent(login_btn)) {
			setElement(login_btn).click();
		}
	}

	public void LoginAsUser(String user, String pwd) throws Exception {
		try {
			enterEmailAddr(user);
			enterPassword(pwd);
			clickLogin();
			waitImplicit();
			waitForAnElement(InstallEditorLink, 50);

		} catch (Exception e) {
			throw new Exception("Login is not Successfull with the set of Data provided");
		}
	}
	
	public boolean islandingPageDisplayed() throws Exception {
		try {
			waitExplicit(InstallEditorLink, 500);
			flag = isElementPresent(InstallEditorLink);
			if (flag) {
				logger.info("Landing Page is displayed");
			} else {
				logger.info("Landing Page is not displayed");

			}
		} catch (Exception e) {
			throw new Exception("landing page is displayed::" + islandingPageDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	public boolean isErrorPageDisplayed() throws Exception {
		try {
			
			String expected_error="invalid mail address/password";
			waitExplicit(error_text, 100);
			String actual_error=setElement(error_text).getText();
			Assert.assertEquals(expected_error, actual_error);
			
		} catch (Exception e) {
			throw new Exception("landing page is displayed::" + islandingPageDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}
	
	
}
