package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AutoTestDomaci3 {
    public static void main(String[] args) throws InterruptedException {

        /*Domaci Zadatak 1:
        Napisati 5 negativnih log-in test case-eva za dati sajt : https://practicetestautomation.com/*/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        WebElement practiceMenuItem = driver.findElement(By.id("menu-item-20"));
        practiceMenuItem.click();

        WebElement testLoginPageLink = driver.findElement(By.linkText("Test Login Page"));
        testLoginPageLink.click();

        String ispravanUsername = "student";
        String ispravanPassword = "Password123";
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));
        WebElement errorMessage = driver.findElement(By.id("error"));

        //Oba polja su prazna
        usernameField.clear();
        passwordField.clear();
        submitButton.click();
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertTrue(errorMessage.getText().contains("username"));

        //Username je ispravno, password je prazno
        driver.navigate().refresh();
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));
        errorMessage = driver.findElement(By.id("error"));
        usernameField.sendKeys(ispravanUsername);
        passwordField.clear();
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assert.assertTrue(errorMessage.getText().contains("password"));

        //Username je prazno, password je ispravno
        driver.navigate().refresh();
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));
        errorMessage = driver.findElement(By.id("error"));
        usernameField.click();
        passwordField.sendKeys(ispravanPassword);
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assert.assertTrue(errorMessage.getText().contains("username"));

        //Username nije ispravno, password je ispravno
        driver.navigate().refresh();
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));
        errorMessage = driver.findElement(By.id("error"));
        usernameField.sendKeys(ispravanUsername+"$");
        passwordField.sendKeys(ispravanPassword);
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assert.assertTrue(errorMessage.getText().contains("username"));

        //Username je ispravno, password nije ispravno
        driver.navigate().refresh();
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));
        errorMessage = driver.findElement(By.id("error"));
        usernameField.sendKeys(ispravanUsername);
        passwordField.sendKeys(ispravanPassword+"$");
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assert.assertTrue(errorMessage.getText().contains("password"));

        System.out.println("Test je uspešan!");
        driver.quit();

    }
}
