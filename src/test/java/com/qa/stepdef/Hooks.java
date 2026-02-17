package com.qa.stepdef;

import com.qa.pages.BasePage;
import com.qa.utils.DriverManager;
import com.qa.utils.ServerManager;
import com.qa.utils.GlobalParams;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;

import java.io.IOException;

public class Hooks {
    private static ServerManager serverManager;
    private static DriverManager driverManager;
    private static boolean initialized = false;
    private AppiumDriver driver;

    @Before
    public void initialize() throws Exception {
        System.out.println("=== HOOKS @Before - Starting ===");

        // Initialize server and driver only once (first scenario)
        if (!initialized) {
            System.out.println("First scenario - initializing server and driver");

            // Initialize global parameters
            GlobalParams params = new GlobalParams();
            params.initializeGlobalParams();
            System.out.println("Global params initialized");

            // Start Appium server
            serverManager = new ServerManager();
            serverManager.startServer();
            System.out.println("Server started");

            // Initialize driver
            driverManager = new DriverManager();
            driverManager.initializeDriver();
            System.out.println("Driver initialized");

            initialized = true;
        }

        // Get the driver
        driver = DriverManager.getDriver();
        System.out.println("Got driver from DriverManager: " + (driver != null ? "SUCCESS" : "NULL"));

        // Ensure driver is not null
        if (driver == null) {
            throw new Exception("Appium driver is not initialized properly.");
        }

        // REMOVED: closeApp() and launchApp() - app stays open between scenarios

        System.out.println("=== HOOKS @Before - Complete ===");
    }

    @After
    public void quit(Scenario scenario) throws IOException {
        System.out.println("=== HOOKS @After - Starting ===");

        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            System.out.println("Screenshot captured for failed scenario");
        }

        System.out.println("=== HOOKS @After - Complete ===");
    }

    // Cleanup after all scenarios
    @io.cucumber.java.AfterAll
    public static void globalTeardown() {
        System.out.println("=== HOOKS @AfterAll - Starting cleanup ===");

        // Quit driver
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            System.out.println("Driver quit successfully");
        }

        // Stop Appium server
        if (serverManager != null && serverManager.getServer() != null) {
            serverManager.getServer().stop();
            System.out.println("Server stopped successfully");
        }

        System.out.println("=== HOOKS @AfterAll - Cleanup complete ===");
    }
}