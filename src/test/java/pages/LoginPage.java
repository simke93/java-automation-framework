package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage  extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;
    @Getter
    @FindBy(id="success")
    private WebElement successAlert;

    private static final String VALID_USER = "user";
    private static final String VALID_PASSWORD = "user";

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL + "login-form.html");
    }

    public void login(){
        usernameInput.sendKeys(VALID_USER);
        passwordInput.sendKeys(VALID_PASSWORD);
        loginButton.click();
    }


}
