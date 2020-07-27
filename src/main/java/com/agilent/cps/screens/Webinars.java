package com.agilent.cps.screens;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;

import com.agilent.cps.core.AutoPopulator;
import com.agilent.cps.widgetactions.GUIWidget;
import com.agilent.cps.widgetactions.Link;
import com.agilent.cps.widgetactions.ListBox;
import com.agilent.cps.widgetactions.TextField;
import com.agilent.cps.widgets.WidgetInfo;

public class Webinars extends BaseScreen{
	
	public static class Widgets {
		public static final WidgetInfo givenName = new WidgetInfo("id=tfa_GivenName", TextField.class);
		public static final WidgetInfo familyName = new WidgetInfo("id=tfa_FamilyName", TextField.class);
		public static final WidgetInfo title = new WidgetInfo("id=tfa_Title", TextField.class);
		public static final WidgetInfo department = new WidgetInfo("id=tfa_Department", TextField.class);
		public static final WidgetInfo companyOrg = new WidgetInfo("id=tfa_CompanyOrg", TextField.class);
		public static final WidgetInfo address = new WidgetInfo("id=tfa_Address", TextField.class);
		public static final WidgetInfo city = new WidgetInfo("id=tfa_City", TextField.class);
		public static final WidgetInfo state = new WidgetInfo("id=tfa_StateProvince", TextField.class);
		public static final WidgetInfo postalCode = new WidgetInfo("id=tfa_PostalCode", TextField.class);
		public static final WidgetInfo country = new WidgetInfo("id=tfa_Country", ListBox.class);
	}

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
	
	public void registerForWebinar() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		switchToFrame();
//		seleniumActions.type(givenName, "Demo");
//		seleniumActions.type(familyName, "Demo");
//		seleniumActions.type(title, "Demo");
//		seleniumActions.type(department, "Demo");
//		seleniumActions.type(companyOrg, "Demo");
//		seleniumActions.type(address, "Demo");
//		seleniumActions.type(city, "Demo");
//		seleniumActions.type(state, "Demo");
//		seleniumActions.type(postalCode, "Demo");
//		seleniumActions.dropdown(country, "India");
		Map<String, String> testdata = new LinkedHashMap<String, String>();
		testdata.put("givenName", "Demo");
		testdata.put("familyName", "Demo");
		testdata.put("title", "Demo");
		testdata.put("department", "Demo");
		testdata.put("companyOrg", "Demo");
		testdata.put("address", "Demo");
		testdata.put("city", "Demo");
		testdata.put("state", "Demo");
		testdata.put("postalCode", "Demo");
		testdata.put("country", "India");
		
		AutoPopulator.populate(this, testdata);
		
	}
	
	public void selectWebinar(String webinarName) {
//		seleniumActions.clickLink(webinarName);
		DM.link(new WidgetInfo("linktext="+webinarName, Link.class)).click();
	}
	
	public void switchToFrame() {
//		driverManager.selectFrame(By.xpath("//div[@id='divframe']/iframe"));
		DMHelper.switchFrame(new WidgetInfo("xpath=//div[@id='divframe']/iframe", GUIWidget.class));
	}
	
}
