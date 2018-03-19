package base;

import org.openqa.selenium.WebDriver;

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

    public boolean assertTitle(String title) {
        return webDriver.getTitle().equals(title);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public void quit() {
        System.out.println(webDriver + " closed");
        webDriver.quit();
    }
}
