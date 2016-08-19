package com.epam.tat.selenium.tests;


import com.epam.tat.selenium.page.*;
import com.epam.tat.selenium.steps.Step;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailTest extends BasicTest {

    private static final String MAIL_TITLE_XPATH = "//a[@title=\"";
    private String subject;
    private String text;

    @Test(description = "input login, password, press login button, check that login was successful", groups = "login test")
    public void loginTest() {
        loginPage = new LoginPage(driver);
        System.out.println("Attempting to login as user1...");
        basePage = loginPage.loginAs(user1);
        System.out.println("Login successful.");
        System.out.println("Verifying if login was successful");
        Assert.assertTrue(basePage.checkLogin(), "Login was insuccessful, logout button not found");
       
        System.out.println("Verification passed");
    }

    @Test(description = "compose email to be send to user2, subject and text fields should be generated automaticly", dependsOnGroups = "login test")
    public void composeMailTest() {
    	System.out.println("Attempting to open new window.");
        Step.composeMail(user2);
    }

    @Test(description = "after composing email press \"save mail\" button", groups = "save as draft", dependsOnMethods = "composeMailTest")
    public void saveMail() {
        composeMailPage.saveAsDraft();
        System.out.println("New mail saved as draft");
    }

    @Test(description = "go to draft folder and check that email presents there", dependsOnMethods = "saveMail")
    public void checkDraftExists() {
    	System.out.println("Attempting to open draft folder...");
    	draftsPage = composeMailPage.goToDraft();
    	draftsPage.waitForPage();
        handleAlert();
        Assert.assertTrue(draftsPage.checkIfMailSaved(user2.getUsername() + "@mail.ru"), "Drafts folder is empty");
        System.out.println("There is saved mail in draft folder");
    }

    @Test(description = "open previously saved mail and check if content was saved properly", dependsOnMethods = "checkDraftExists")
    public void checkDraftContentTest() {
    	composeMailPage =  draftsPage.openSavedMail();
    	composeMailPage.waitForPage();
        Assert.assertTrue(composeMailPage.checkMailContent(user2.getUsername() + "@mail.ru", subject, text), "Not all elements were found successfully.");
    }

    @Test(description = "press \"send\" button to send the mail", dependsOnMethods = "checkDraftExists")
    public void sendEmail() {
    	System.out.println("Trying to send draft mail...");
        composeMailPage.sendMail();
        System.out.println("Mail sent");
        
    }

  
    @Test(description = "go to \"sent\" folder and check if message presents there", dependsOnMethods = "sendEmail")
    public void checkThatEmailWasSend() {
        System.out.println("Open sent folder");
        sentPage = basePage.goToSent();
        sentPage.waitForPage();
        System.out.println("Check sent mail folder");
        Assert.assertTrue(sentPage.checkIfMailSaved(user2 + "@mail.ru"), "Sent folder is empty");
    }

    @Test(description = "go to draft and make sure that email no longer presents at this folder", dependsOnMethods = "sendEmail")
    public void checkEmailNotAtDrafts() {
        System.out.println("Opening drafts folder.");
        draftsPage = sentPage.goToDraft();
        draftsPage.waitForPage();
        System.out.println("Check if mail disappeared from drafts folder");
        Assert.assertFalse(draftsPage.checkIfMailSaved(user2.getUsername() + "@mail.ru"), "Drafts folder is not empty");
    }
}
