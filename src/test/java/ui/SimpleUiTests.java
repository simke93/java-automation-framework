package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleUiTests {
    WebDriver driver;


    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void openSiteTest() {
        driver.get(BASE_URL);

    }

    @Test
    void openWebFormPageTest() throws InterruptedException {
        driver.get(BASE_URL);

        driver.findElement(By.xpath("//a[@href='web-form.html']")).click();
        driver.findElement(By.xpath("//button[text()='Submit']")).click();


        Thread.sleep(2000);
        WebElement title = driver.findElement(By.className("display-6"));
        Assertions.assertEquals("Form submitted", title.getText());

    }

}
