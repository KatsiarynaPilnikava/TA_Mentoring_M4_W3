package com.epam.tat.selenium.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class SentPage extends BasePage {
    private static final String MAIL_TITLE_XPATH = "//a[@title=\"";
    private static final String BODY = "//body";
    @FindBy(xpath = BODY)
    private WebElement page;
    private WebDriver driver;
    private WebElement sentMail;

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkIfMailSaved(String mailTo) {
        goToDraft();
        try {
            sentMail = driver.findElement(By.xpath(MAIL_TITLE_XPATH + mailTo + "\"]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clearSentMails() {
        page.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        page.sendKeys(Keys.DELETE);
    }
}
