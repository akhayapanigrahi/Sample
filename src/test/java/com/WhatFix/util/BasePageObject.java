package com.WhatFix.util;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BasePageObject {

	public WebDriver driver;
	public WebElement element;
	
	public BasePageObject(WebDriver driver){
	this.driver = driver;	

	}
	
	public void waitForAnElement(final By theElement,int timeoutInSeconds) {
		try {
			WebElement element = driver.findElement(theElement);
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Reporter.log("There was an issue in finding the webelement, " + theElement.getClass() + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public boolean isElementPresent(By by) {
	
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
	public WebElement setElement(By by) throws Exception {
        try {
        	element = driver.findElement(by);
        } catch (NoSuchElementException e) {
        	
        	throw new Exception("Element is located while:"+element+e.getLocalizedMessage());
        }
        
        return element;
    }
	
	public  String getText(By theElement) {
		
		WebElement element= driver.findElement(theElement);
		return element.getText();
	}
    
	public  String getCurrentLocation() {
		return driver.getCurrentUrl().trim();
	}
	
	public void waitImplicit() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public  void waitImplicit(int millisecods) {
		driver.manage().timeouts().implicitlyWait(millisecods,
		TimeUnit.MILLISECONDS);
		try {
			Thread.sleep(millisecods);
		} catch (InterruptedException e) {
		}
	}
	
	public  void waitExplicit(By locator, int timeOutInSeconds) {
		new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public void switchToNewWindow() throws Exception
	{
		for (String winHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
		}
	}

public  String getPageTitle()
{
		return driver.getTitle().trim();
}
	
	}
