package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PraticandoWaitExercicio {
    private final String URL = "https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver";

    private WebDriver driver;

    private WebDriverWait wait;

    @BeforeEach
    void beforeEach() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(URL);
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    void test1Alert() {
        WebElement element = driver.findElement(By.id("alert"));
        element.click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("I got opened after 5 secods",alert.getText());
        alert.accept();
    }

    @Test
    void test2Text() {
        WebElement element = driver.findElement(By.id("h2"));
        Assertions.assertEquals("site",element.getText());

        WebElement botaoTitulo = driver.findElement(By.id("populate-text"));
        botaoTitulo.click();

        wait.until(ExpectedConditions.textToBe(By.id("h2"),"Selenium Webdriver"));
        Assertions.assertEquals("Selenium Webdriver",element.getText());
    }

    @Test
    void test3BotaoEscondido() {
        driver.findElement(By.id("display-other-button")).click();

        WebElement element = driver.findElement(By.id("hidden"));

        wait.until(ExpectedConditions.visibilityOf(element));

        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            Assertions.fail("elemento não foi encontrado");
        }

    }

    @Test
    void test4BotaoDesabilitado() {
        driver.findElement(By.id("enable-button")).click();

        WebElement element = driver.findElement(By.id("disable"));

        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
    }

    @Test
    void test5CheckBox() {
        WebElement element = driver.findElement(By.id("ch"));

        Assertions.assertFalse(element.isSelected());

        driver.findElement(By.id("checkbox")).click();

        wait.until(ExpectedConditions.elementSelectionStateToBe(By.id("ch"),true));

        Assertions.assertTrue(element.isSelected());

    }
}
