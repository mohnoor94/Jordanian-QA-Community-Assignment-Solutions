import base.AppManager;
import driver.Driver;
import driver.DriverType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment01Test {
    private AppManager app;

    @BeforeClass
    public void initialize() {
        // Firefox driver in headless mode has been set, (try other options!)
        app = new AppManager(Driver.getNewDriver(DriverType.FIREFOX, true));
    }

    @AfterClass
    public void cleanUp() {
        app.quit();
    }

    @Test
    public void amazonTest() {
        String expectedTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, * * * Books, DVDs & more";
        app.maximize()
                .goTo("https://www.amazon.com/")
                .isTitleMatch(expectedTitle);
        Assert.assertEquals(app.getTitle(), expectedTitle);
    }

    @Test
    public void facebookTest() {
        app.goTo("http://www.facebook.com")
                .back()
                .printCurrentUrl()
                .forward()
                .refresh();
        Assert.assertNotNull(app.getTitle());
    }
}