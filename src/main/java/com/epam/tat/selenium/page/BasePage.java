package com.epam.tat.selenium.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

import com.epam.tat.selenium.util.JSUtil;



public class BasePage {
    private static final String NEW_MAIL_XPATH = "//span[@class=\"b-toolbar__btn__text b-toolbar__btn__text_pad\"]";
    private static final String DRAFTS_XPATH = "//a[@href=\'/messages/drafts/\']";
    private static final String SENT_XPATH = "//a[@href=\'/messages/sent/\']";
    private static final String LOGOUT_LINK_ID = "PH_logoutLink";
    private static final String BODY = "//body";
    private static final String GET_PAGE_TITLE_SCRIPT = "return document.title;";
    private static final String WAIT_FOR_PAGE_SCRIPT = "return document.readyState;";
    @FindBy(xpath = BODY)
    private WebElement page;
    @FindBy(xpath = NEW_MAIL_XPATH)
    private WebElement newMailButton;
    @FindBy(xpath = DRAFTS_XPATH)
    private WebElement draftButton;
    @FindBy(xpath = SENT_XPATH)
    private WebElement sentButton;
    @FindBy(id = LOGOUT_LINK_ID)
    private WebElement logoutButton;
    public static WebDriver driver;
    protected static JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        System.out.println(String.format("Navigated to \"%s\" page", JSUtil.getTitle(GET_PAGE_TITLE_SCRIPT)));
    }


   /* public static String getTitle() {
        return js.executeScript(GET_PAGE_TITLE_SCRIPT).toString();
    }*/

	public ComposeMailPage composeNewMail() {
		mouseClick(newMailButton);
        return new ComposeMailPage(driver);
    }

    public DraftsPage goToDraft() {
         mouseClick(draftButton);
		return new DraftsPage(driver);
        
    }

    public SentPage goToSent() {
    	mouseClick(sentButton);
		return new SentPage(driver);
    }

    public void logout() {
        logoutButton.click();
    }

    public boolean checkLogin() {
    	 return logoutButton.isDisplayed();
    }
    public void clearField(WebElement element){
    	element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }
    @Deprecated
    public void waitForPage(){
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    public void mouseClick(WebElement element){
    	Actions action = new Actions(driver);
    	action.moveToElement(element).click().build().perform();
    	JSUtil.waitForPage(WAIT_FOR_PAGE_SCRIPT);
    	
    }
    
}
