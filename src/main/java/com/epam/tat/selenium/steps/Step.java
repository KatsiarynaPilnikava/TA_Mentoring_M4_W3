package com.epam.tat.selenium.steps;

import org.apache.commons.lang3.RandomStringUtils;
import com.epam.tat.selenium.entities.User;
import com.epam.tat.selenium.page.BasePage;
import com.epam.tat.selenium.page.ComposeMailPage;

public class Step {
	    private static String subject;
	    private static String text;
	    protected static ComposeMailPage composeMailPage;
	    protected static BasePage basePage;

	  public static void composeMail(User user2) {
		    subject = RandomStringUtils.randomAlphabetic(8);
	        text = RandomStringUtils.randomAlphabetic(12);
	        composeMailPage = basePage.composeNewMail();
	        composeMailPage.composeMail(user2.getUsername() + "@mail.ru", subject, text);
	  }
}
