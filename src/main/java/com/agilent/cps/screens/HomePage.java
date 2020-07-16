package com.agilent.cps.screens;

import org.openqa.selenium.By;

public class HomePage extends BaseScreen {

	By cpsMenuLink = By.xpath("//div[@class='FPdoLc tfB0Bf']//input[@name='btnK']");
	By searchText = By.name("q");
	
	public void clickCpsMenulink() {
		seleniumActions.type(searchText, "Agilent");
		seleniumActions.click(cpsMenuLink);
		seleniumActions.clickLink("agl");
	}
	
}
