package com.agilent.cps.screens;

import org.openqa.selenium.By;

public class Webinars extends BaseScreen{

	By givenName = By.id("tfa_GivenName");
	By familyName = By.id("tfa_FamilyName");
	By title = By.id("tfa_Title");
	By department = By.id("tfa_Department");
	By companyOrg = By.id("tfa_CompanyOrg");
	By address = By.id("tfa_Address");
	By city = By.id("tfa_City");
	By state = By.id("tfa_StateProvince");
	By postalCode = By.id("tfa_PostalCode");
	By country = By.id("tfa_Country");
	
	public void registerForWebinar() {
		switchToFrame();
		seleniumActions.type(givenName, "Demo");
		seleniumActions.type(familyName, "Demo");
		seleniumActions.type(title, "Demo");
		seleniumActions.type(department, "Demo");
		seleniumActions.type(companyOrg, "Demo");
		seleniumActions.type(address, "Demo");
		seleniumActions.type(city, "Demo");
		seleniumActions.type(state, "Demo");
		seleniumActions.type(postalCode, "Demo");
		seleniumActions.dropdown(country, "India");
	}
	
	public void selectWebinar(String webinarName) {
		seleniumActions.clickLink(webinarName);
	}
	
	public void switchToFrame() {
		driverManager.selectFrame(By.xpath("//div[@id='divframe']/iframe"));
	}
	
}
