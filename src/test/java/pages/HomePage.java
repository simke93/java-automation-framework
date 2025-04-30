package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[contains(@href, 'login-form')]")
    private WebElement loginButton;


    public  HomePage(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL);

    }

    @Step("Open login page")
    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage(driver);

    }
}
