package com.epam.tat.module4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainTest {
	public static final String LOGIN_KEY = "katsiaryna.pilnikava";
	public static final String PASS_KEY = "aki180992";
	WebDriver driver = new FirefoxDriver();

	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG tng = new TestNG();
		tng.addListener(tla);
		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");

		List<String> files = new ArrayList<String>();
		files.addAll(new ArrayList<String>() {
			{
				add("testng.xml");
			}
		});
		suite.setSuiteFiles(files);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		tng.setXmlSuites(suites);

		tng.run();
	}

	@BeforeTest
	public void goToPage() {
		WebDriver driver = new FirefoxDriver();
		System.out.println("Driver added");
		driver.get("https://mail.yandex.by/");
		System.out.println("Page title is: " + driver.getTitle());
	}

	

	@Test
	public void login(String loginKey, String passKey) {
		WebElement login = driver.findElement(By.name("login"));
		WebElement pass = driver.findElement(By.name("passwd"));
		login.sendKeys(LOGIN_KEY);
		pass.sendKeys(PASS_KEY);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[@id='nb-1']/span[1]"))
						.getText(), "katsiaryna.pilnikava@yandex.ru");
		System.out.println("Login successful");
	}

	@Test
	public void createDraft() {
		WebElement theme = driver.findElement(By
				.xpath("//input[@id='compose-subj' and @name='subj']"));
		WebElement receiver = driver
				.findElement(By
						.cssSelector("html#js.m-locale_ru.m-layout_2pane.m-skin-blue.notranslate.js.geolocation.draganddrop.borderimage.cssanimations.cssgradients.csstransforms.csstransitions.classlist.filereader.blobconsructor.no-is-pdd.draganddrop-files.input-multiple.classlist-second-arg.no-msie.no-webkit.no-opera.mozilla.no-ie9.no-ie10.no-ie11.no-ielt11.no-ios.no-mac.b-page_type_compose.b-page_service_mail.m-loaded body div#js-page.b-page div.b-page__content div.block-app div.b-layout.js-layout div.b-layout__right div.b-layout__right__content div.block-right-box div.b-right-box.js-right-box-wrapper div.b-right-box__content div.block-mail-right-box div.block-compose div.b-layout__inner.b-layout__inner_type_compose.js-compose-block div.b-compose form.compose-form.compose-tinyMCE table.b-compose-head tbody tr.b-compose-head__field.b-compose-head__field_to.js-compose-field-wrapper__to td.b-compose-head__field__value div.b-mail-input.b-mail-input_yabbles.js-compose-mail-input.js-compose-mail-compose-input.js-compose-mail-input_to input.b-mail-input_yabbles__focus"));
		WebElement draft = driver.findElement(By
				.xpath("//a[@href='#draft' and  @title='Черновики']"));
		WebElement save = driver
				.findElement(By
						.xpath("//button[@data-action='dialog.save' and  @data-nb='button']"));
		driver.findElement(By.xpath("//a[@data-action='compose.go']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		theme.sendKeys("Test");
		receiver.sendKeys("aki4takayasu@gmail.com");
		theme.sendKeys(Keys.TAB, "Test message");
		draft.click();
		save.click();
		Assert.assertEquals(
				driver.findElement(
						By.xpath("//a/span[2]/span/span[@title='aki4takayaus@gmail.com']"))
						.getText(), "aki4takayasu@gmail.com");
		System.out.println("Draft saved");
	}

	@Test
	public void sendDraft() {
		WebElement send = driver.findElement(By
				.xpath("//button[@id='compose-submit' and @type='submit']"));
		WebElement sentFolder = driver.findElement(By
				.xpath("//a[@href='#sent' and @title='Отправленные']"));
		WebElement theme = driver.findElement(By
				.xpath("//input[@id='compose-subj' and @name='subj']"));
		driver.findElement(By.xpath("//a/span[1]/span/span[@ title='Test']"))
				.click();
		// Assert.assertEquals(
		// driver.findElement(By.xpath("//form/table/tbody/tr[3]/td[2]/div[2]/div/div[1]/input")).getText(),"aki4takayasu@gmail.com");
		// Assert.assertEquals(driver.findElement(By.xpath("//input[@id='compose-subj' and @name='subj']")).getText(),"Test");
		// theme.sendKeys(Keys.TAB);
		// Assert.assertEquals( theme,"Test message");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		theme.sendKeys(Keys.TAB, "Test message");
		send.click();
		// Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div/div[5]/div/div[3]/div/div[3]/div/div/div/div[1]/div[3]/div[1]/div/div[2]/div[2]/div/div[1]")).getText(),"В папке «Черновики» нет писем.");
		System.out.println("Draft sent");
		sentFolder.click();
		Assert.assertEquals(
				driver.findElement(
						By.xpath("//a/span[1]/span/span[@title='Test message']"))
						.getText(), "Test message");
		System.out.println("Draft sent");

	}
	@AfterTest
	public void saveScreenshotAndCloseBrowser() throws IOException {
		driver.quit();
	}
}
