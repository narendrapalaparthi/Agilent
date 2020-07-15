package com.agilent.cps.tests;


import java.lang.reflect.Method;

import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.agilent.cps.utils.Browser;
import com.agilent.cps.utils.DriverManager;

public class BaseTest implements ITest{
	
	Browser browser = new Browser();
	String testname;
	
	@BeforeMethod(alwaysRun = true)
	public void launchBrowser(Method method) {
		testname=method.getName();
		browser.startBrowser("https://www.google.co.in/");
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		DriverManager.getInstance().tearDown();
	}

	@Override
	public String getTestName() {
		return testname;
	}
}
