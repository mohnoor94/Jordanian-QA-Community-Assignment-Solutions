import base.AppManager;
import driver.Driver;
import driver.DriverType;

/**
 * @author AbuKhleif
 * http://www.abukhleif.com/
 */
public class Main {
    public static void main(String[] args) {
        // Firefox driver in headless mode has been set, (try other options!)
        AppManager app = new AppManager(Driver.getNewDriver(DriverType.FIREFOX, true));

        app.maximize()
                .goTo("https://www.amazon.com/")
                .isTitleMatch("Amazon.com: Online Shopping for Electronics, Apparel, Computers, * * * Books, DVDs & more")
                .goTo("http://www.facebook.com")
                .back()
                .printCurrentUrl()
                .forward()
                .refresh()
                .quit();
    }
}