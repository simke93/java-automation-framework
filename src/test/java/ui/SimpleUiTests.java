package ui;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;


public class SimpleUiTests extends BaseTest {

    @Test
    void openSiteTest() {
        driver.get(BASE_URL);

    }

    @Test
    void openWebFormPageTest() {

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get(BASE_URL);

        driver.findElement(By.xpath("//a[@href='web-form.html']")).click();
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        longWait.until(ExpectedConditions.urlContains("submitted-form.html"));
        WebElement title = driver.findElement(By.className("display-6"));
        Assertions.assertEquals("Form submitted", title.getText());

    }

    @Test
    void loginTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.openLoginPage();

        loginPage.login();

        Assertions.assertEquals("Login successful", loginPage.getSuccessAlert().getText());

    }


}
