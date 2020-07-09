package com.agilent.cps.common;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.agilent.cps.utils.Browser;
import com.agilent.cps.utils.ElementManager;

public class BaseTest{
	
	@BeforeMethod
	public void startBrowser() {
		Browser browser = new Browser();
		browser.startBrowser("https://www.google.co.in/");
	}
	
	@AfterMethod
	public void teardown() {
		ElementManager.getInstance().tearDown();
	}
}
