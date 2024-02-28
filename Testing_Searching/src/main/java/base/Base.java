package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

    public static WebDriver driver;
    public static Properties prop = new Properties();
    public static Properties loc = new Properties();
    public static Logger log;
    public static FileInputStream fr;
    public static FileInputStream fr1;

    @BeforeTest
    public void setUp() throws IOException {
        log = LogManager.getLogger(Base.class);

        if (driver != null) {
            driver.quit();
        }

        fr = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
        fr1 = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/locators.properties");

        prop.load(fr);
        loc.load(fr1);

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            log.info("Chrome Browser instantiated");
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.privatebrowsing.autostart", true); // Enable private browsing mode
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            driver = new FirefoxDriver(options);
            log.info("Firefox Browser instantiated");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Example wait time, adjust as needed
        driver.get(prop.getProperty("testurl"));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser closed successfully");
    }

    // Method to clear search box on the website
    public void clearSearchBox() {
        driver.findElement(By.id(loc.getProperty("search_bar"))).clear();
    }
}
