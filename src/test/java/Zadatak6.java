import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak6 {
    public static void main(String[] args) {

        // Zadatak 6
        // Testirati log out funkcionalnost

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
        username.sendKeys("student");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Password123");

        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        WebElement logOutButton = driver.findElement(By.cssSelector("#loop-container > div > article > div.post-content > div > div > div > a"));
        Assert.assertTrue(logOutButton.isDisplayed());

        logOutButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");

        System.out.println("Test je uspešan.");
        driver.quit();
    }
}
