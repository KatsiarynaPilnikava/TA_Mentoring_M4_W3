package com.epam.tat.selenium.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSUtil {
	
	protected static JavascriptExecutor js;
	
	public static String getTitle(String script) {
        return js.executeScript(script).toString();
    }
	public static void waitForPage(String script) {
		js.executeScript(script).toString().equals("complete");
	}
	 public static void highligtElement(WebElement element, String highLightScript, int timeout, String disable){
			
	    	js.executeScript(highLightScript, element);
	        try {
	            Thread.sleep(timeout);
	        } catch (InterruptedException interruptedException) {
	            System.out.println(String.format("Unexpected exception:\n %s", interruptedException));
	        }
	        js.executeScript(disable, element);
	    }

}
