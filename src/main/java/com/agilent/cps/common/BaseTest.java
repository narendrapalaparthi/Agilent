package com.agilent.cps.common;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.agilent.cps.utils.Browser;
import com.agilent.cps.utils.DriverManager;

public class BaseTest{
	
	Browser browser = new Browser();
	
	@BeforeMethod
	public void startBrowser() {
		browser.startBrowser("https://www.google.co.in/");
	}
	
	@AfterMethod
	public void teardown() {
		DriverManager.getInstance().tearDown();
	}
}
