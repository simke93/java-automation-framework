package pages;

import configs.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    static TestConfig configProperties = ConfigFactory.create(TestConfig.class,
            System.getProperties());

    protected static final String BASE_URL = configProperties.getUiBaseUrl();
    //protected static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
}
