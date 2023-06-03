package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

public class Constants {
    public static final int WAIT_TIME = 1000;
    public static final String PROPERTY_FILE_PATH = System.getProperty("user.dir")+"/src/test/resources/Config/config.properties";

    public static final String SCREENSHOT_FILEPATH=System.getProperty("user.dir") +"/screenshots/";

}
