package com.agilent.cps.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtility {
	
	private static final ScreenShotUtility screenShotUtility = new ScreenShotUtility();
	public static DriverManager driverManager = DriverManager.getInstance();
	
	public static ScreenShotUtility getInstance()
	{
		return screenShotUtility;
	}
	
	public ScreenShotUtility() {
		File file = new File(Configuration.screenshotPath);
		if(!file.exists())
			file.mkdirs();
	}
	
	public void takeWindowScreenshot(String imageName, WebDriver driver)
	{
		try {
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(Configuration.screenshotPath+imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void takeScrollingScreenshot(String imageName, WebDriver driver)
	{
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			long bodyscrollheight = (long) js.executeScript("return document.body.scrollHeight");
			
			long bodyheight = (long) js.executeScript("return document.body.clientHeight");

			long docscrollheight = (long) js.executeScript("return document.documentElement.scrollHeight");
			
			long docheight = (long) js.executeScript("return document.documentElement.clientHeight");
			long windowWidth = (long) js.executeScript("return document.body.clientWidth");
			
			long windowheight =(long) Math.min(Math.min(bodyheight, bodyscrollheight),Math.min(docscrollheight,docheight)) ;
			long scrollheight = (long) Math.max(Math.max(bodyheight, bodyscrollheight),Math.max(docscrollheight,docheight));
			int iterations = (int) (scrollheight/windowheight);
			if(scrollheight/windowheight>0)
				iterations++;
			BufferedImage result = new BufferedImage((int)windowWidth,(int)scrollheight,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = result.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			int y=0;
			String command = "window.scrollTo(0,0);";
			for(int i=0; i<=iterations; i++)
			{
				js.executeScript(command);
				File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				driverManager.sleep(.5);
				Image bi;
				
					bi = ImageIO.read(source);
					if(scrollheight-y<windowheight)
					{
						g.drawImage(bi, 0,(int)(scrollheight-windowheight), (int)windowWidth, (int)windowheight, null);
						break;
					}
					g.drawImage(bi, 0,y, (int)windowWidth, (int)windowheight , null);
					y+=windowheight;
					command = "window.scrollTo(0,"+(windowheight+(windowheight*i))+");";
			}
			ImageIO.write(result, "png", new File(Configuration.screenshotPath+imageName));
			command = "window.scrollTo(0,0);";
			driverManager.sleep(.5);
			js.executeScript(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
