package com.agilent.cps.tests;

import org.testng.annotations.Test;

import com.agilent.cps.screens.AgilentUniversity;
import com.agilent.cps.screens.Webinars;

public class tests extends BaseTest{
	
	@Test(groups= {"test"})
	public void registerAWebinor() {
		Webinars webinarPage = new Webinars();
		webinarPage.navigatToWebinarsPage();
		webinarPage.selectWebinar("Solutions for Light Hydrocarbons and Gases");
		webinarPage.registerForWebinar();
	}
	
	@Test(groups= {"test"})
	public void registerAElearningCourse() {
		AgilentUniversity agilentUniversity = new AgilentUniversity();
		agilentUniversity.navigatToAgilentUniversityPage();
		agilentUniversity.accessElearningCourse("Agilent University ePass - Unlimited Self-paced Online Course Access");
	}
	
}
