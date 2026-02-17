package com.qa.utils;

public class GlobalParams {
    private static ThreadLocal<String> platformName = new ThreadLocal<>();
    private static ThreadLocal<String> udid = new ThreadLocal<>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static ThreadLocal<String> systemPort = new ThreadLocal<>();
    private static ThreadLocal<String> chromeDriverPort = new ThreadLocal<>();

    // Setters and getters for platform-related parameters
    public void setPlatformName(String platformName1) {
        platformName.set(platformName1);
    }

    public String getPlatformName() {
        return platformName.get();
    }

    public String getUDID() {
        return udid.get();
    }

    public void setUDID(String udid2) {
        udid.set(udid2);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName2) {
        deviceName.set(deviceName2);
    }

    public String getSystemPort() {
        return systemPort.get();
    }

    public void setSystemPort(String systemPort2) {
        systemPort.set(systemPort2);
    }

    public String getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort2) {
        chromeDriverPort.set(chromeDriverPort2);
    }


    public void initializeGlobalParams() {
        // Set default values or read from system properties
        setPlatformName(System.getProperty("platformName", "Android"));
        if (getPlatformName() == null || getPlatformName().isEmpty()) {
            setPlatformName("Android");
        }
        setUDID(System.getProperty("udid", "emulator-5554"));
        setDeviceName(System.getProperty("deviceName", "Pixel_5"));


        if (getPlatformName().equals("Android")) {
            setSystemPort(System.getProperty("systemPort", "10000"));
            setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
        } else {
            throw new IllegalStateException("Invalid Platform Name! Only Android is supported.");
        }
    }
}
