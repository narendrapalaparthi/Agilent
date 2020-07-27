package com.agilent.cps.widgetactions;

import com.agilent.cps.core.DriverManager;
import com.agilent.cps.core.DriverManagerHelper;
import com.agilent.cps.utils.Logger;


public interface IGUIWidget{
	
	public static final DriverManager manager = DriverManager.getInstance();
	public static final DriverManagerHelper managerHelper = DriverManagerHelper.getInstance();
	public static final Logger logger = Logger.getInstance();
	
	public void setDisplayValue(String value);
	
	public String getDisplayValue();
}
