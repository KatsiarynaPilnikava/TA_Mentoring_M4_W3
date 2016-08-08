package com.epam.tat.selenium.tests;

import com.epam.tat.selenium.entities.User;
import com.epam.tat.selenium.factory.CapabilitiesFactory;
import com.epam.tat.selenium.factory.WebDriverFactory;
import com.epam.tat.selenium.page.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import java.util.concurrent.TimeUnit;

public class BasicTest {

	private static final int TIMEOUT = 20;
	protected WebDriver driver;
	protected User user1;
	protected User user2;
	protected LoginPage loginPage;
	protected BasePage basePage;
	protected ComposeMailPage composeMailPage;
	protected DraftsPage draftsPage;
	protected SentPage sentPage;

	@BeforeClass
	public void initUsers() {
		user1 = new User("pilnikava_1", "1UserPassword");
		user2 = new User("pilnikava_2", "2UserPassword");
	}

	@BeforeTest
	@Parameters({ "grid_url", "url", "browser" })
	public void initStartPage(String grid_url, String url, String browser) {
		System.out.println(String.format("Initializing %s browser driver", browser));
        if ("".equals(grid_url)) {
            driver = WebDriverFactory.getWebDriver(browser);
        } else {
            driver = WebDriverFactory.getWebDriver(grid_url, CapabilitiesFactory.getDesiredCapabilities(browser));
        }
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        System.out.println(String.format("Browser driver for  %s browser was successfully initialized", browser));
        driver.get(url);

	}

	@AfterTest
	public void clearEmailAndDisposeDriver() {
		basePage = loginPage.loginAs(user1);
		draftsPage = basePage.goToDraft();
		handleAlert();
		draftsPage.clearDrafts();
		sentPage = draftsPage.goToSent();
		handleAlert();
		sentPage.clearSentMails();
		sentPage.logout();
		driver.close();
	}

	protected void handleAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException ignore) {
		}
	}
}
