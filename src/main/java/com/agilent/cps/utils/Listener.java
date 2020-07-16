package com.agilent.cps.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("################## Test Started : "+result.getName()+" ##############");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		result.getThrowable().printStackTrace();
		ScreenShotUtility.getInstance().takeScrollingScreenshot(result.getTestName(), DriverManager.getInstance().getActiveDriver());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		result.getThrowable().printStackTrace();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

}
