import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Zadatak7 {
    public static void main(String[] args) {

        // Zadatak 7
        // Testirati neuspesan log in

        WebDriverManager.chromedriver().setup();
        WebDriver driver =  new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://practicetestautomation.com/");

        WebElement practice = driver.findElement(By.id("menu-item-20"));
        practice.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice/");

        WebElement testLoginPage = driver.findElement(By.cssSelector("#loop-container > div > article > div.post-content > div.wp-block-columns.is-layout-flex.wp-container-core-columns-layout-1.wp-block-columns-is-layout-flex > div:nth-child(1) > p > a"));
        testLoginPage.click();

        WebElement pageTitle = driver.findElement(By.cssSelector("#login > h2"));
        Assert.assertEquals(pageTitle.getText(), "Test login");

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("student1");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Password123");

        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        WebElement error = driver.findElement(By.cssSelector("#error"));
        Assert.assertTrue(error.isDisplayed());

        username.sendKeys("student");
        password.sendKeys("Password1234");
        submit.click();
        Assert.assertTrue(error.isDisplayed());

        username.sendKeys("student");
        password.clear();
        submit.click();
        Assert.assertTrue(error.isDisplayed());

        username.clear();
        password.sendKeys("Password123");
        submit.click();
        Assert.assertTrue(error.isDisplayed());

        System.out.println("Test je uspešan.");
        driver.quit();
    }
}
