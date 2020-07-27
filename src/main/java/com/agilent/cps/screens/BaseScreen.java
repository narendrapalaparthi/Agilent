package com.agilent.cps.screens;

import org.openqa.selenium.By;

import com.agilent.cps.core.DriverManagerHelper;
import com.agilent.cps.utils.DriverManager;
import com.agilent.cps.utils.SeleniumActions;
import com.agilent.cps.widgetactions.Link;
import com.agilent.cps.widgets.WidgetInfo;

public class BaseScreen {

	DriverManager driverManager = DriverManager.getInstance();
	SeleniumActions seleniumActions = SeleniumActions.getInstance();
	
	com.agilent.cps.core.DriverManager DM = com.agilent.cps.core.DriverManager.getInstance();
	DriverManagerHelper DMHelper = DriverManagerHelper.getInstance();
	
	public static class Widgets{
		public static final WidgetInfo traingEventsMenuLink = new WidgetInfo("id=MegaMenu-TrainingEvents", Link.class);
		public static final WidgetInfo webinarsLink = new WidgetInfo("id=MegaMenu-Webinars", Link.class);
		public static final WidgetInfo educationLink = new WidgetInfo("id=MegaMenu-Education", Link.class);
	}
	
	By traingEventsMenuLink = By.id("MegaMenu-TrainingEvents");
	
	By webinarsLink = By.id("MegaMenu-Webinars");
	
	By educationLink = By.id("MegaMenu-Education");
	
	
	public void navigatToWebinarsPage() {
//		seleniumActions.click(traingEventsMenuLink);
//		seleniumActions.click(webinarsLink);
		
		DM.link(Widgets.traingEventsMenuLink).click();
		DM.link(Widgets.webinarsLink).click();
	}
	
	public void navigatToAgilentUniversityPage() {
//		seleniumActions.click(traingEventsMenuLink);
//		seleniumActions.click(educationLink);
		
		DM.link(Widgets.traingEventsMenuLink).click();
		DM.link(Widgets.educationLink).click();
	}
	
}
