package com.agilent.cps;

import org.testng.annotations.Test;

import com.agilent.cps.common.BaseTest;

public class tests extends BaseTest{
	
	@Test(groups= {"test"})
	public void simpletest() {
		System.out.println("sample test");
	}
	
}
