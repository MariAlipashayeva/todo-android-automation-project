package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.HashMap;

public class ServerManager {
    private static final ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    // Method to get the Appium server instance
    public AppiumDriverLocalService getServer() {
        return server.get();
    }

    // Method to get the Appium server URL
    public String getUrl() {
        AppiumDriverLocalService appiumServer = server.get();
        System.out.println("Getting server URL, server exists: " + (appiumServer != null));
        if (appiumServer != null) {
            System.out.println("Server running: " + appiumServer.isRunning());
        }

        if (appiumServer != null && appiumServer.isRunning()) {
            String url = appiumServer.getUrl().toString();
            System.out.println("Server URL: " + url);
            return url;
        } else {
            throw new RuntimeException("Appium server is not started or not accessible.");
        }
    }

    // Method to start the Appium server
    public void startServer() {
        utils.log().info("Starting Appium server...");
        System.out.println("Starting Appium server...");

        // Use the appropriate method for your platform (Mac/Windows) to get the Appium service
        AppiumDriverLocalService appiumServer = getAppiumService();
        appiumServer.start();

        // Check if the server started successfully
        if (appiumServer == null || !appiumServer.isRunning()) {
            utils.log().fatal("Appium server did not start. ABORT!!!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server did not start. ABORT!!!");
        }

        server.set(appiumServer);
        System.out.println("Appium server started at: " + appiumServer.getUrl());
        utils.log().info("Appium server started at: " + appiumServer.getUrl());
    }

    // Get the Appium service for your platform (for Mac in this case)
    public AppiumDriverLocalService getAppiumService() {
        GlobalParams params = new GlobalParams();
        HashMap<String, String> environment = new HashMap<>();
        environment.put("/Users/alipasayevamari/.sdkman/candidates/java/current/bin:/Users/alipasayevamari/Library/Android/sdk/tools:/Users/alipasayevamari/Library/Android/sdk/platform-tools:/opt/homebrew/bin:/opt/homebrew/sbin:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/local/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/appleinternal/bin", System.getenv("PATH"));
        environment.put("ANDROID_HOME", "/Users/alipasayevamari/Library/Android/sdk");
        environment.put("JAVA_HOME", "/Users/alipasayevamari/.sdkman/candidates/java/current");

        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/opt/homebrew/bin/node"))
                .withAppiumJS(new File("/opt/homebrew/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment));
    }
}