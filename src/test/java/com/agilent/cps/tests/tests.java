package com.agilent.cps.tests;

import org.testng.annotations.Test;

import com.agilent.cps.screens.HomePage;

public class tests extends BaseTest{
	
	@Test(groups= {"test"})
	public void simpletest() {
		HomePage homePage = new HomePage();
		homePage.clickCpsMenulink();
	}
	
}
