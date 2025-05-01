package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class BaseTest {

    WebDriver driver;

    protected static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = initDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Check initDriver() logic.");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    private WebDriver initDriver() {

        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        System.out.println(" DEBUG: SELENIUM_REMOTE_URL = " + remoteUrl);
        if (remoteUrl != null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");  // Add headless mode
            options.addArguments("--disable-gpu"); // Switch off GPU, because we don't need it in headless mode
            options.addArguments("--no-sandbox"); // Switch off sandbox to prevent access rights issues
            options.addArguments("--disable-dev-shm-usage"); // Use /tmp instead of /dev/shm
            options.setCapability("goog:loggingPrefs", Map.of("browser", "ALL"));
            try {
                driver = new RemoteWebDriver(new URL(remoteUrl), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Malformed URL for Selenium Remote WebDriver", e);
            }
        } else {
            driver = new ChromeDriver();
        }
       driver.manage().window().maximize();
       return driver;
}


}
