package com.agilent.cps.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Stopwatch;

public class DriverManager {

	public WebDriver driver = null;
	public Map<String, WebDriver> driverMap = new HashMap<String, WebDriver>();
	public static final DriverManager driverManager = new DriverManager();

	/*
	 * Sigleton Implementation
	 */
	public static DriverManager getInstance() {
		return driverManager;
	}

	/*
	 * Setting driver instance
	 */
	public void setActiveDriver(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Return Active Driver Instance
	 */
	public WebDriver getActiveDriver() {
		return this.driver;
	}

	/*
	 * Return JavaScriptExecuter on Active Driver Instance
	 */
	public JavascriptExecutor getJSExecutor() {
		return (JavascriptExecutor) this.driver;
	}

	/*
	 * To handle Multiple Driver Instances
	 */
	public Map<String, WebDriver> getDriverMap() {
		return this.driverMap;
	}

	/*
	 * Wait Maximum of timeOutInSeconds for the existence of Web Element(Based On By
	 * Locator)
	 */
	public void waitForWidget(By locator, long timeOutInSeconds, double d) {
		Stopwatch sw = Stopwatch.createStarted();
		sleep(.5);
		do {
			if (driver.findElements(locator).size() > 0)
				return;
			try {
				Thread.sleep((long) (d * 1000));
			} catch (InterruptedException e) {
			}
		} while (sw.elapsed(TimeUnit.MILLISECONDS) < (timeOutInSeconds * 1000));
		sw.stop();
	}

	public void selectFrame(By frameLocator) {
		WebElement element = driver.findElement(frameLocator);
		driver.switchTo().frame(element);
	}

	/*
	 * Check for the existence of Web Element(Based On By Locator) Maximum of
	 * timeOutInSeconds
	 */
	public boolean widgetExists(By byLocator, long timeOutInSeconds, double intervalInSeconds) {
		Stopwatch sw = Stopwatch.createStarted();
		do {
			if (driver.findElements(byLocator).size() > 0)
				return true;
			try {
				Thread.sleep((long) (intervalInSeconds * 1000));
			} catch (InterruptedException e) {
			}
		} while (sw.elapsed(TimeUnit.MILLISECONDS) < (timeOutInSeconds * 1000));
		sleep(.5);
		sw.stop();
		return false;
	}

	/*
	 * Check for the Non existence of Web Element(Based On Locator)
	 * Maximum of timeOutInSeconds
	 */
	public boolean widgetNotExists(By locator, long timeOutInSeconds,
			double intervalInSeconds) {
		Stopwatch sw = Stopwatch.createStarted();
		do {
			if (driver.findElements(locator).size() == 0)
				return true;
			try {
				Thread.sleep((long) (intervalInSeconds * 1000));
			} catch (InterruptedException e) {
			}
		} while (sw.elapsed(TimeUnit.MILLISECONDS) < (timeOutInSeconds * 1000));
		sleep(.5);
		sw.stop();
		return false;
	}

	/*
	 * Check for the Visibility of Web Element(Based On Locator)
	 * Maximum of timeOutInSeconds
	 */
	public boolean widgetVisible(By locator, long timeOutInSeconds, double interval) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, (long) (interval * 1000));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			sleep(.5);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Check Whether the Web Element(Based On Locator) enabled or not
	 * for Maximum of timeOutInSeconds
	 */
	public boolean widgetEnabled(By locator, long timeOutInSeconds, double interval) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, (long) (interval * 1000));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			sleep(.5);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Wait till the Ajax completed max 30 Secs
	 */
	public void waitForAjaxToLoad() {
		Stopwatch sw = Stopwatch.createStarted();
		String style = null;
		do {
			sleep(.5);
			WebElement element = driver.findElement(By.linkText("Identity Manager"));
			style = element.getAttribute("style");
			if (null != style && (!"cursor: wait;".equals(style))) {
				sleep(1);
				return;
			}
		} while (sw.elapsed(TimeUnit.MILLISECONDS) < (30 * 1000));
		sw.stop();
	}

	/*
	 * Wait till the page loaded max 30 secs
	 */
	public void waitForPageToLoad() {
		By locator = By.xpath("//span[text()='Please wait...')]");
		WebDriverWait wait = new WebDriverWait(driver, 180, 1);
		try {
			sleep(.5);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return;
		} catch (Exception e) {
		}

	}

	/*
	 * To switch Window using Window Title
	 */
	public void switchWindow(String winTitle) {
		if (winTitle.equalsIgnoreCase(driver.getTitle()))
			return;
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			if (winTitle.equalsIgnoreCase(driver.getTitle()))
				break;
		}
	}

	/*
	 * Quit WebDriver
	 */
	public void tearDown() {
		if (null != this.driver)
			this.driver.quit();
		sleep(2);
	}

	/*
	 * Sleep in seconds
	 */
	public void sleep(double timeInSeconds) {
		try {
			Thread.sleep((long) (timeInSeconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Return Web Element based on Field Name & Style
	 */
	public WebElement getWebElement(By locator) {
		waitForPageToLoad();
		WebElement element = driver.findElement(locator);
		return element;
	}

}
