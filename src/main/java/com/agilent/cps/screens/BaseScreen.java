package com.agilent.cps.screens;

import org.openqa.selenium.By;

import com.agilent.cps.utils.DriverManager;
import com.agilent.cps.utils.SeleniumActions;

public class BaseScreen {

	DriverManager driverManager = DriverManager.getInstance();
	SeleniumActions seleniumActions = SeleniumActions.getInstance();
	
	By traingEventsMenuLink = By.id("MegaMenu-TrainingEvents");
	
	By webinarsLink = By.id("MegaMenu-Webinars");
	
	By educationLink = By.id("MegaMenu-Education");
	
	
	public void navigatToWebinarsPage() {
		seleniumActions.click(traingEventsMenuLink);
		seleniumActions.click(webinarsLink);
	}
	
	public void navigatToAgilentUniversityPage() {
		seleniumActions.click(traingEventsMenuLink);
		seleniumActions.click(educationLink);
	}
	
}
