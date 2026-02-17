package com.qa.utils;


import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.CapabilityType;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class CapabilityManager {
    TestUtils utils = new TestUtils();

    // Get the Android capabilities using AndroidOptions
    public UiAutomator2Options getCaps() throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();

        try {
            utils.log().info("Getting capabilities");

            // Create AndroidOptions instead of DesiredCapabilities
            UiAutomator2Options options = new UiAutomator2Options();

            // Set common capabilities
            options.setCapability(CapabilityType.PLATFORM_NAME, params.getPlatformName());
            options.setCapability("appium:udid", params.getUDID());
            options.setCapability("appium:deviceName", params.getDeviceName());

            // Set Android-specific capabilities
            if (params.getPlatformName().equals("Android")) {
                options.setCapability("appium:automationName", props.getProperty("androidAutomationName"));
                options.setCapability("appium:appPackage", props.getProperty("androidAppPackage"));
                options.setCapability("appium:appActivity", props.getProperty("androidAppActivity"));

                // Define the app path
                String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                        + File.separator + "resources" + File.separator + "apps" + File.separator + "todo.apk";
                utils.log().info("App URL is: " + androidAppUrl);
                options.setCapability("app", androidAppUrl);
            }

            return options;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
