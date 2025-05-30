package pages;

import configs.TestConfig;
import io.qameta.allure.Step;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage  extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(id = "success-alert")
    private WebElement successAlert;

    static TestConfig configProperties = ConfigFactory.create(TestConfig.class,
            System.getProperties());

    private static final String VALID_USER = configProperties.getLogin();
    private static final String VALID_PASSWORD = configProperties.getPassword();
    //private static final String VALID_USER = "user";
    //private static final String VALID_PASSWORD = "user";

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL + "login-form.html");
    }


    @Step("Login")
    public void login() {
        usernameInput.sendKeys(VALID_USER);
        passwordInput.sendKeys(VALID_PASSWORD);
        loginButton.click();
    }

    public WebElement getSuccessAlert() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("success")));


    }

}
