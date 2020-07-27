package com.agilent.cps.author;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.agilent.cps.components.BaseComponent;
import com.agilent.cps.utils.ReadExcel;

public class AuthorTests extends BaseAuthorTest{
	
	public static Map<String, List<Map<String, String>>> dataMap = new LinkedHashMap<String, List<Map<String, String>>>();
	BaseComponent componentObject = null;
	
	public AuthorTests() {
		dataMap = (new ReadExcel()).getDataFromExcel("Authoring_TestData.xlsx");
	}
	
	@Test(groups= {"author"}, dataProvider = "getAuthorTestNames")
	public void runAuthorTests(String component, String testName, int rowCount) throws Exception {
		Map<String, String> rowData = dataMap.get(component).get(rowCount);
		componentObject=screenMap.get(component);
		if(null != componentObject)
		{
			componentObject.populate(rowData);
		}
		else
			throw new Exception("Unable to find screen object");
	}
	
	@DataProvider(name="getAuthorTestNames")
	public Object[][] getTestNames(){
		Map<String, String> testDetails = new LinkedHashMap<String, String>();
		for(Map<String, String> rowData : dataMap.get("TestCases")){
			String componentName = rowData.get("ComponentName");
			String fieldValue = rowData.get("tests");
			List<Map<String, String>> componentData = dataMap.get(componentName);
			if(fieldValue.contains("-")) {
				int start = Integer.parseInt(fieldValue.split("-")[0].trim())-1;
				int end = Integer.parseInt(fieldValue.split("-")[1].trim());
				int index = start;
				for(Map<String, String> componentRowData : componentData.subList(start, end)) {
					testDetails.put(componentRowData.get("TestName"), componentName+"#"+index);
					index++;
				}
			}else {
				int index = Integer.parseInt(fieldValue)-1;
				testDetails.put(componentData.get(index).get("TestName"), componentName+"#"+index);
			}
				
		}
		Object [][] array = new Object[testDetails.size()][3];
		int count = 0;
		for(String testName : testDetails.keySet()) {
			String value = testDetails.get(testName);
			array[count][0] = value.split("#")[0];
			array[count][1] = testName;
			array[count][2] = Integer.parseInt(value.split("#")[1]);
			count++;
		}
		
		return array;
	}
	
	public List<Map<String, String>> getDataMap(String iterations)
	{
		if(iterations.contains("("))
		{
			String num = iterations.replaceAll(".*\\(|\\).*", "").trim();
			String sheet = iterations.substring(0, iterations.lastIndexOf("("));
			int start = Integer.parseInt(num.split(",")[0].trim())-1;
			int end = Integer.parseInt(num.split(",")[1].trim());
			return dataMap.get(sheet).subList(start, end);
		}
		else
			return dataMap.get(iterations);			
	}
	
}
