package com.agilent.cps.screens;

import com.agilent.cps.widgetactions.Link;
import com.agilent.cps.widgets.WidgetInfo;

public class AgilentUniversity extends BaseScreen{
	
	public void accessElearningCourse(String course) {
//		seleniumActions.clickLink("Access online e-learning");
//		seleniumActions.clickLinkWithParialText(course);
		
		DM.link(new WidgetInfo("linktext=Access online e-learning", Link.class)).click();
		DM.link(new WidgetInfo("partiallinktext="+course, Link.class)).click();
	}

}
