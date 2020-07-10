package com.agilent.cps.utils;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class FieldActions {
	
	public static final FieldActions fieldActions = new FieldActions();
	public static DriverManager driverManager = DriverManager.getInstance();
	
	/*
	 * Singleton Implementation
	 */
	public static FieldActions getInstance()
	{
		return fieldActions;
	}

	public void textField(String fieldName, String fieldValue) {
		WebElement element = driverManager.getWebElement(fieldName, "Text");
		System.out.println("WebElement created");
		if("".equals(fieldValue.trim())){
			element.clear();
			System.out.println("Text cleared");
		}
		else{
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"), fieldValue+Keys.TAB);
			System.out.println("Text entered in Text field");
		}
	}
	
	public void textArea(String fieldName, String fieldValue) {
		WebElement element = driverManager.getWebElement(fieldName, "TextArea");
		System.out.println("WebElement created");
		element.clear();
		System.out.println("Text cleared");
		element.sendKeys(fieldValue+Keys.TAB);
		System.out.println("Text entered in Text Area");
	}
	
	public void radioButtonSingleSelect(String fieldName, String fieldValue) {
		WebElement element = driverManager.getWebElement("//fieldset[@id='"+fieldName+"']", "RadioButtonSingleSelect");
		System.out.println("WebElement created");
		List<WebElement> inputElements = element.findElements(By.tagName("input"));
		if(Pattern.matches("[0-9]*", fieldValue))
			driverManager.getJSExecutor().executeScript("arguments[0].click();",inputElements.get(Integer.parseInt(fieldValue)));
		else
			for(WebElement ele : inputElements)
			{
				if(fieldValue.equals(ele.getAttribute("value")) || fieldValue.equals(ele.getAttribute("title"))){
					ele.click();
					break;
				}
			}
		
		System.out.println("Radio Button Clicked");
	}
	
	public void radioButton(String fieldName, String fieldValue) {
		int index = fieldName.lastIndexOf("_");
		String identifier = "//input[@name='"+fieldName.substring(0,index)+"' and @value='"+fieldName.substring(index+1)+"']";
		driverManager.getWebElement(identifier, "Radio").click();
		System.out.println("Radio Button Clicked");
	}
	
	public void checkBox(String fieldName, String fieldValue) {
		WebElement element = driverManager.getWebElement(fieldName, "CheckBox");
		System.out.println("WebElement created");
		if(element.isSelected() ^ Boolean.parseBoolean(fieldValue))
			element.click();
		System.out.println("Checkbox Checked");
	}
	
	public void dropdown(String fieldName, String fieldValue) {
		WebElement element = driverManager.getWebElement(fieldName, "Dropdown");
		System.out.println("WebElement created");
		Select select = new Select(element);
		if(Pattern.matches("[0-9]*", fieldValue))
			select.selectByIndex(Integer.parseInt(fieldValue));
		else
			select.selectByVisibleText(fieldValue);
		System.out.println("Option Selected");
	}
	
	public void multiSelect(String fieldName, String fieldValue){
		WebElement element = driverManager.getWebElement(fieldName, "Dropdown");
		System.out.println("WebElement created");
		Select select = new Select(element);
		for(WebElement ele : select.getAllSelectedOptions())
			ele.click();
		element = driverManager.getWebElement(fieldName, "Dropdown");
		select = new Select(element);
		if(Pattern.matches("[0-9]*", fieldValue))
			select.getOptions().get(Integer.parseInt(fieldValue)).click();
		else
			select.selectByVisibleText(fieldValue);
		System.out.println("Option Selected");
	}
	
	public void optionSelector(String fieldName, String fieldValue){
		WebElement element = driverManager.getWebElement(fieldName+".Options", "Dropdown");
		System.out.println("WebElement created");
		Select select = new Select(element);
		if(Pattern.matches("[0-9]*", fieldValue))
			select.selectByIndex(Integer.parseInt(fieldValue));
		else
			select.selectByVisibleText(fieldValue);
		click("action."+fieldName+".OptionSelectorAdd");
		System.out.println("Option Selected");
	}
	
	public void click(String fieldName){
		WebElement element = driverManager.getWebElement(fieldName, "Button");
		System.out.println("WebElement Created");
		element.click();
		System.out.println("Click Successful");
		driverManager.waitForPageToLoad();
	}
	
	public void clickButton(String fieldName){
		String identifier = "//button[contains(text(),'" + fieldName + "')]";
		WebElement element = driverManager.getWebElement(identifier, "Button");
		System.out.println("WebElement Created");
		element.click();
		System.out.println("Click Successful");
		driverManager.waitForPageToLoad();
	}
	
	public void clickLink(String fieldName){
		WebElement element = driverManager.getActiveDriver().findElement(By.linkText(fieldName));
		System.out.println("WebElement Created");
		element.click();
		System.out.println("Click Successful");
		driverManager.waitForPageToLoad();
	}
	
	public void type(WebElement element, String fieldValue){
		if("".equals(fieldValue.trim())){
			element.clear();
			System.out.println("Text cleared");
		}
		else{
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"), fieldValue+Keys.TAB);
			System.out.println("Text entered in Text field");
		}
	}
	
	public void setFilePath(String fieldName, String filePath){
		WebDriver driver = DriverManager.getInstance().getActiveDriver();
		if(Boolean.parseBoolean(Configuration.isRCServer)){
			LocalFileDetector fileDetector = new LocalFileDetector();
			((RemoteWebDriver)driver).setFileDetector(fileDetector);
			File file = fileDetector.getLocalFile(filePath);
			filePath = file.getAbsolutePath();
		}
		WebElement element = driver.findElement(DriverManager.getInstance().getLocator(fieldName, "text"));
		((JavascriptExecutor)driver).executeScript("arguments[0].style = ''; arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", element);
		element.sendKeys(filePath);
	}
	
	public String getText(String fieldName, String fieldType, Boolean xpathConstructor){
		WebElement element = driverManager.getWebElement(fieldName, fieldType);
		System.out.println("WebElement Created");
		return element.getText();
	}
	
	public String getAttribute(String fieldName, String fieldType, Boolean xpathConstructor, String attribute){
		WebElement element = driverManager.getWebElement(fieldName, fieldType);
		System.out.println("WebElement Created");
		return element.getAttribute(attribute);
	}
	
	public String getValue(String locator, String fieldType){
		WebElement element = driverManager.getWebElement(locator, fieldType);
		return element.getAttribute("value");
	}
}
