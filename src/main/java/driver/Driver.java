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

    static {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
        System.setProperty("webdriver.gecko.driver",
                System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver.exe");
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