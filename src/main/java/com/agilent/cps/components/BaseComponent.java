package com.agilent.cps.components;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.agilent.cps.core.AutoPopulator;

public class BaseComponent {

	public void populate(Map<String, String> rowData) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("################################ ROWDATA ############################");
		System.out.println(rowData);
//		AutoPopulator.populate(this, rowData);
	}

}
