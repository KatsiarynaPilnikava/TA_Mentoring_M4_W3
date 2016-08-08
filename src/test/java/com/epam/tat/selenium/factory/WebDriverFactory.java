package com.epam.tat.selenium.factory;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class WebDriverFactory {
	private static final String CHROME_PROPERTY = "webdriver.chrome.driver";
	private static final String PATH_TO_CHROME_BROWSER = "D:\\soft\\Drivers\\chromedriver.exe";
	
	public static WebDriver getWebDriver(String browser) {
        WebDriver driver = null;
        if ("google_chrome".equals(browser)) {
            System.setProperty(CHROME_PROPERTY, PATH_TO_CHROME_BROWSER);
            driver = new ChromeDriver();
        } else if ("firefox".equals(browser)) {
            driver = new FirefoxDriver();
        }
        return driver;
    }
	 public static WebDriver getWebDriver(String url, DesiredCapabilities capabilities) {
		 
	        WebDriver driver = null;
	        try {
	            driver = new RemoteWebDriver(new java.net.URL(url), capabilities);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return driver;
	    }
}

