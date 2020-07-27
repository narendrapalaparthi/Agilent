package com.agilent.cps.author;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.reflections.Reflections;
import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.agilent.cps.components.BaseComponent;
import com.agilent.cps.utils.Logger;
import com.agilent.cps.utils.Browser;
import com.agilent.cps.utils.Constants;
import com.agilent.cps.utils.DriverManager;

public class BaseAuthorTest implements ITest{
	
	Browser browser = new Browser();
	String testname;
	Map<String, BaseComponent> screenMap = new HashMap<String, BaseComponent>();
	
	Logger logger = Logger.getInstance();
	public static Date startTime;
	
	@BeforeSuite(alwaysRun = true)
	public void setup() throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		startTime = Calendar.getInstance().getTime();
		configurationSetup();
		logSetupInformation();
		Reflections ref = new Reflections("com.agilent.cps.components");
		Set<Class<? extends BaseComponent>> components = ref.getSubTypesOf(BaseComponent.class);
		for(Class<?> screen : components)
		{
			BaseComponent obj;
			try {
				obj = (BaseComponent) screen.getDeclaredConstructor().newInstance();
				screenMap.put(obj.getClass().getSimpleName(), obj );
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	@AfterSuite(alwaysRun = true)
	public void generateReport() {
		logger.verificationPoint("result=true Total number of verification points: "+(Logger.success+Logger.fail)+" Passed verification points: "+Logger.success+" Failed verification points: "+Logger.fail, true);
		logger.info("Total time elapsed : "+getTotalTimeLapsed());
		if(Logger.error == 0 && Logger.fail == 0)
			logger.status("Passed");
		else
			logger.status("Failed");
		try {
			writeXML();
			generateHtml();
		} catch (TransformerException | FileNotFoundException e) {
			logger.error(e);
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchBrowser(Object[] args) {
		testname=(String) args[1];
//		browser.startBrowser("https://www.agilent.com/");
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		DriverManager.getInstance().tearDown();
	}

	@Override
	public String getTestName() {
		return testname;
	}
	
	private static String getTotalTimeLapsed() {
		long diff = System.currentTimeMillis() - startTime.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000);
		String time = "";
		for(String s : (diffHours+":"+diffMinutes+":"+diffSeconds).split(":"))
		{
			if(s.length()<=1)
				s="0"+s;
			time += s+":";
		}
		return time.substring(0, time.length()-1);
	}

	private void logSetupInformation() {
		logger.info("Execution Time Stamp : "+startTime.toString());
		logger.info("Launching in Browser  : "+Constants.browser);
	}

	public static void writeXML () throws TransformerException
	{
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(Logger.doc);
		StreamResult target = new StreamResult(Logger.logFile);
		
		transformer.transform(source, target);
	}
	
	public static void generateHtml() throws FileNotFoundException, TransformerException
	{
		TransformerFactory tFactory=TransformerFactory.newInstance();

        Source xslDoc=new StreamSource("lib/NewStylesheet.xsl");
        Source xmlDoc=new StreamSource(Logger.logFile);

        OutputStream htmlFile=new FileOutputStream(Logger.logFile.replace(".xml", ".html"));
        Transformer trasform=tFactory.newTransformer(xslDoc);
        trasform.transform(xmlDoc, new StreamResult(htmlFile));
	}
	
	public void configurationSetup() {
		String browser = System.getProperty("browser");
		String isRCServer = System.getProperty("isRCServer");
		String port = System.getProperty("port");
		
		if(!("".equals(browser) || null == browser))
			Constants.browser = browser.toUpperCase();
		
		if(!("".equals(isRCServer) || null == isRCServer))
			Constants.isrcserver = isRCServer;
		
		if(!("".equals(port) || null == port))
			Constants.port = port;
	}
	
}
