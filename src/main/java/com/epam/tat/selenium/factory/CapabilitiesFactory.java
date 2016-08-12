package com.epam.tat.selenium.factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesFactory {
	public static DesiredCapabilities getDesiredCapabilities(String browser) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if ("opera".equals(browser)) {
            desiredCapabilities = DesiredCapabilities.operaBlink();
            desiredCapabilities.setBrowserName("opera");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
        } else if ("google_chrome".equals(browser)) {
            desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setBrowserName("chrome");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
        } else if ("firefox".equals(browser)) {
            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setBrowserName("firefox");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
        }
        return desiredCapabilities;
    }

}
