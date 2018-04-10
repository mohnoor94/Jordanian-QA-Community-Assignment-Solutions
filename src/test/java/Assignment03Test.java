import base.AppManager;
import com.google.common.collect.Streams;
import driver.Driver;
import driver.DriverType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Assignment03Test {
    private AppManager app;

    @BeforeClass
    public void initialize() {
        app = new AppManager(Driver.getNewDriver(DriverType.CHROME, false));
    }

    @AfterClass
    public void cleanUp() {
        app.quit();
    }

    @Test(priority = 1)
    public void openFlipkart() {
        String expectedTitle = "Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More";
        app.goTo("https://www.flipkart.com/");
        Assert.assertEquals(app.getTitle(), expectedTitle);
    }

    @Test(priority = 2)
    public void discardPopupIfFound() {
        By popupLocator = By.className("_32LSmx");
        if (app.findElement(popupLocator) != null) {
            app.click(By.xpath("//button[text()='✕']"));
        }
        Assert.assertNull(app.findElement(popupLocator));
    }

    @Test(priority = 3)
    public void getNumberOfLinks() {
        System.out.println("You have '"
                + app.findElements(By.tagName("a")).size()
                + "' link(s) in this page.");
    }

    @Test(priority = 4)
    public void printAllLinks() {
        List<WebElement> links = app.findElements(By.tagName("a"));
        System.out.println("All links list (filterd to valid URLs only):\n");
        Streams.mapWithIndex(
                links.stream()
                        .map(link -> link.getAttribute("href"))
                        .filter(link -> link.startsWith("http")), (link, index) -> (index + 1) + " ==> " + link)
                .forEach(System.out::println);
    }
}