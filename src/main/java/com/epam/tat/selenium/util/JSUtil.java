package com.epam.tat.selenium.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSUtil {
	
	private static final String WAIT_FOR_PAGE_SCRIPT = "return document.readyState;";
	protected static JavascriptExecutor js;
	
	public static String getTitle(String script) {
        return js.executeScript(script).toString();
    }
	public static void waitForPage() {
		js.executeScript(WAIT_FOR_PAGE_SCRIPT).toString().equals("complete");
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
