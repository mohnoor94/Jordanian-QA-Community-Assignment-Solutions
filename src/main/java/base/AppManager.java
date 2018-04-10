package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AppManager {
    private WebDriver webDriver;

    public AppManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public AppManager maximize() {
        webDriver.manage().window().maximize();
        return this;
    }

    public AppManager goTo(String url) {
        webDriver.get(url);
        return this;
    }

    public AppManager refresh() {
        webDriver.navigate().refresh();
        return this;
    }

    public AppManager forward() {
        webDriver.navigate().forward();
        return this;
    }

    public AppManager back() {
        webDriver.navigate().back();
        return this;
    }

    public AppManager isTitleMatch(String title) {
        System.out.println(assertTitle(title) ? "PASS" : "FAIL");
        return this;
    }

    public AppManager printCurrentUrl() {
        System.out.println(getCurrentUrl());
        return this;
    }

    public WebElement findElement(By locator) {
        try {
            return webDriver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<WebElement> findElements(By locator) {
        return webDriver.findElements(locator);
    }

    public boolean click(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            element.click();
            return true;
        }
        return false;
    }

    public boolean isPageContains(String value) {
        return getPageContents().contains(value);
    }

    public String getPageContents() {
        return webDriver.getPageSource();
    }

    public boolean assertTitle(String title) {
        return webDriver.getTitle().equals(title);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public void quit() {
        System.out.println(webDriver + " closed");
        webDriver.quit();
    }
}
