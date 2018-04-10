package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Driver {
    private static Map<Integer, WebDriver> drivers = new HashMap<>();
    private static DriverType defaultDriverType = DriverType.CHROME;
    private static boolean defaultHeadlessMode = false;
    private static final String OS = System.getProperty("os.name");
    private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");

    static {
        setDriverPaths();
    }

    private static void setDriverPaths() {
        String folder = null;
        boolean isWindows = false;
        if (OS.startsWith("Linux")) folder = "linux";
        else if (OS.startsWith("Windows")) {
            isWindows = true;
            folder = "windows";
        } else {
            System.out.println("We are sorry but only Windows and Linux are supported right now.");
            System.exit(0);
        }
        System.setProperty("webdriver.chrome.driver",
                PROJECT_DIRECTORY + File.separator + "drivers" + File.separator + folder
                        + File.separator + "chromedriver" + (isWindows ? ".exe" : ""));
        System.setProperty("webdriver.gecko.driver",
                PROJECT_DIRECTORY + File.separator + "drivers" + File.separator + folder
                        + File.separator + "geckodriver" + (isWindows ? ".exe" : ""));
    }

    public static WebDriver getNewDriver(DriverType driverType, boolean headless) {
        drivers.put(drivers.size() + 1, createWebDriver(driverType, headless));
        return drivers.get(drivers.size());
    }

    public static WebDriver getAnyDriver() {
        WebDriver driver;
        if (drivers.isEmpty()) {
            driver = createWebDriver(defaultDriverType, defaultHeadlessMode);
            drivers.put(1, driver);
            return driver;
        }
        int random = new Random().nextInt(drivers.size());
        driver = drivers.get(random);
        drivers.put(random, driver);
        return driver;
    }

    public static WebDriver getDriver(int index) {
        return drivers.getOrDefault(index, createWebDriver(defaultDriverType, defaultHeadlessMode));
    }

    private static WebDriver createWebDriver(DriverType driverType, boolean headless) {
        return driverType == DriverType.CHROME
                ? headless
                ? new ChromeDriver(new ChromeOptions().setHeadless(true))
                : new ChromeDriver()
                : headless
                ? new FirefoxDriver(new FirefoxOptions().setHeadless(true))
                : new FirefoxDriver();
    }
}