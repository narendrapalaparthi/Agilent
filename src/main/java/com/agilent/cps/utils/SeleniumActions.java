package com.agilent.cps.utils;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumActions {
	
	public static final SeleniumActions fieldActions = new SeleniumActions();
	public static DriverManager driverManager = DriverManager.getInstance();
	
	/*
	 * Singleton Implementation
	 */
	public static SeleniumActions getInstance()
	{
		return fieldActions;
	}

	public void textField(By locator, String fieldValue) {
		WebElement element = driverManager.getWebElement(locator);
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
	
	public void textArea(By locator, String fieldValue) {
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement created");
		element.clear();
		System.out.println("Text cleared");
		element.sendKeys(fieldValue+Keys.TAB);
		System.out.println("Text entered in Text Area");
	}
	
	public void radioButtonSingleSelect(By locator, String fieldValue) {
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement created");
		List<WebElement> inputElements = element.findElements(By.tagName("input"));
		if(Pattern.matches("[0-9]*", fieldValue))
			inputElements.get(Integer.parseInt(fieldValue)).click();
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
	
	public void radioButton(By locator) {
		driverManager.getWebElement(locator).click();
		System.out.println("Radio Button Clicked");
	}
	
	public void checkBox(By locator, String fieldValue) {
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement created");
		if(element.isSelected() ^ Boolean.parseBoolean(fieldValue))
			element.click();
		System.out.println("Checkbox Checked");
	}
	
	public void dropdown(By locator, String fieldValue) {
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement created");
		Select select = new Select(element);
		if(Pattern.matches("[0-9]*", fieldValue))
			select.selectByIndex(Integer.parseInt(fieldValue));
		else
			select.selectByVisibleText(fieldValue);
		System.out.println("Option Selected");
	}
	
	public void multiSelect(By locator, String fieldValue){
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement created");
		Select select = new Select(element);
		for(WebElement ele : select.getAllSelectedOptions())
			ele.click();
		element = driverManager.getWebElement(locator);
		select = new Select(element);
		if(Pattern.matches("[0-9]*", fieldValue))
			select.getOptions().get(Integer.parseInt(fieldValue)).click();
		else
			select.selectByVisibleText(fieldValue);
		System.out.println("Option Selected");
	}
	
	public void click(By locator){
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement Created");
		element.click();
		System.out.println("Click Successful");
		driverManager.waitForPageToLoad();
	}
	
	public void clickButton(By locator){
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement Created");
		element.click();
		System.out.println("Click Successful");
		driverManager.waitForPageToLoad();
	}
	
	public void clickLink(String linkText){
		WebElement element = driverManager.getActiveDriver().findElement(By.linkText(linkText));
		System.out.println("WebElement Created");
		element.click();
		System.out.println("Click Successful");
		driverManager.waitForPageToLoad();
	}
	
	public void clickJs(By locator){
		WebElement element = driverManager.getActiveDriver().findElement(locator);
		System.out.println("WebElement Created");
		driverManager.getJSExecutor().executeScript("arguments[0].click();", element);
		System.out.println("Click Successful");
		driverManager.waitForPageToLoad();
	}
	
	public void type(By locator, String fieldValue){
		WebElement element = driverManager.getActiveDriver().findElement(locator);
		if("".equals(fieldValue.trim())){
			element.clear();
			System.out.println("Text cleared");
		}
		else{
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"), fieldValue+Keys.TAB);
			System.out.println("Text entered in Text field");
		}
	}
	
	public void setFilePath(By locator, String filePath){
		WebDriver driver = DriverManager.getInstance().getActiveDriver();
		if(Boolean.parseBoolean(Configuration.isRCServer)){
			LocalFileDetector fileDetector = new LocalFileDetector();
			((RemoteWebDriver)driver).setFileDetector(fileDetector);
			File file = fileDetector.getLocalFile(filePath);
			filePath = file.getAbsolutePath();
		}
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].style = ''; arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", element);
		element.sendKeys(filePath);
	}
	
	public String getText(By locator, Boolean xpathConstructor){
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement Created");
		return element.getText();
	}
	
	public String getAttribute(By locator, String attribute){
		WebElement element = driverManager.getWebElement(locator);
		System.out.println("WebElement Created");
		return element.getAttribute(attribute);
	}
	
	public String getValue(By locator, String fieldType){
		WebElement element = driverManager.getWebElement(locator);
		return element.getAttribute("value");
	}
	
	public String getTitle() {
		return driverManager.getActiveDriver().getTitle();
	}
	
	public void doubleClick(By locator) {
		WebElement element = driverManager.getWebElement(locator);
		Actions actions = new Actions(driverManager.getActiveDriver());
		actions.doubleClick(element).build().perform();
	}
	
	public void contextClick(By locator) {
		WebElement element = driverManager.getWebElement(locator);
		Actions actions = new Actions(driverManager.getActiveDriver());
		actions.contextClick(element).build().perform();
	}

	public void focusElement(By locator) {
		WebElement element = driverManager.getWebElement(locator);
		Actions actions = new Actions(driverManager.getActiveDriver());
		actions.moveToElement(element).build().perform();
	}

	public void dragAndDrop(By sourceLocator, By destinationLocator) {
		WebElement source = driverManager.getWebElement(sourceLocator);
		WebElement destination = driverManager.getWebElement(destinationLocator);
		Actions actions = new Actions(driverManager.getActiveDriver());
//		actions.moveToElement(source).clickAndHold().moveToElement(destination).release().build().perform();
		actions.dragAndDrop(source, destination);
		
	}
	
	
}
