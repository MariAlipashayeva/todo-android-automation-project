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



        if (!initialized) {
            System.out.println("First scenario - initializing server and driver");

            GlobalParams params = new GlobalParams();
            params.initializeGlobalParams();
            System.out.println("Global params initialized");


            serverManager = new ServerManager();
            serverManager.startServer();
            System.out.println("Server started");


            driverManager = new DriverManager();
            driverManager.initializeDriver();
            System.out.println("Driver initialized");

            initialized = true;
        }


        driver = DriverManager.getDriver();


        if (driver == null) {
            throw new Exception("Appium driver is not initialized properly.");
        }


    }

    @After
    public void quit(Scenario scenario) throws IOException {
        System.out.println("=== HOOKS @After - Starting ===");

        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

        }

    }

    @io.cucumber.java.AfterAll
    public static void globalTeardown() {
        System.out.println("=== HOOKS @AfterAll - Starting cleanup ===");

        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            System.out.println("Driver quit successfully");
        }

        if (serverManager != null && serverManager.getServer() != null) {
            serverManager.getServer().stop();

        }


    }
}