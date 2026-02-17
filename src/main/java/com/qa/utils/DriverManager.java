package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;

public class DriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    // Make this static so it can be called without an instance
    public static AppiumDriver getDriver() {
        AppiumDriver currentDriver = driver.get();
        System.out.println("Getting driver from ThreadLocal: " + (currentDriver != null ? "Driver exists" : "Driver is NULL"));
        return currentDriver;
    }

    // Set the current Appium driver
    public void setDriver(AppiumDriver driver2) {
        System.out.println("Setting driver in ThreadLocal: " + (driver2 != null ? "Setting valid driver" : "Setting NULL driver"));
        driver.set(driver2);
    }

    // Initialize the driver for Android
    public void initializeDriver() throws Exception {
        System.out.println("Starting driver initialization...");
        if (driver.get() == null) {
            AppiumDriver appiumDriver = null;
            GlobalParams params = new GlobalParams();
            PropertyManager props = new PropertyManager();

            try {
                utils.log().info("Initializing Appium driver for Android");

                // Initialize global parameters
                params.initializeGlobalParams();

                // Only Android is considered here
                if (params.getPlatformName().equals("Android")) {
                    // Use ServerManager to get the Appium server URL
                    URL serverUrl = new URL(new ServerManager().getUrl());
                    System.out.println("Appium server URL: " + serverUrl);

                    appiumDriver = new AndroidDriver(serverUrl, new CapabilityManager().getCaps());
                    System.out.println("AndroidDriver created successfully");
                } else {
                    throw new Exception("Unsupported platform: " + params.getPlatformName());
                }

                if (appiumDriver == null) {
                    throw new Exception("Appium driver initialization failed. ABORT!!!");
                }

                utils.log().info("Android Driver initialized successfully");
                driver.set(appiumDriver);
                System.out.println("Driver set in ThreadLocal");

            } catch (IOException e) {
                e.printStackTrace();
                utils.log().info("Driver initialization failed: " + e.toString());
                throw new Exception("Driver initialization failure: " + e.getMessage(), e);
            }
        } else {
            System.out.println("Driver already initialized, skipping...");
        }
    }
}