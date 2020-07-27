package com.agilent.cps.widgetactions;

import org.openqa.selenium.WebElement;

import com.agilent.cps.widgets.WidgetInfo;

public class Label extends GUIWidget implements IGUIWidget {

	public Label(WidgetInfo widgetInfo) {
		super(widgetInfo);
	}
	
	@Override
	public void setDisplayValue(String value) {
		value = value.toUpperCase();
		WebElement element = managerHelper.getWebElement(widgetInfo);
		element.click();
	}

	@Override
	public String getDisplayValue() {
		WebElement element = managerHelper.getWebElement(widgetInfo);
		return element.getText().trim();
	}
	

}
