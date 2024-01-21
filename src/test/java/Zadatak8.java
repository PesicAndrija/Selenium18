import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Zadatak8 {
    public static void main(String[] args) {

        //Zadatak 8
        //Testirati log in na wordpress stranicu

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wordpress.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement logInButton = driver.findElement(By.cssSelector("#wpcom-home > div.lp-root > nav > ul:nth-child(2) > li:nth-child(1) > a"));
        logInButton.click();

        String ispravanUsername = "salvage4389@laziness8926.anonaddy.me";
        WebElement usernameBox = driver.findElement(By.cssSelector("input#usernameOrEmail"));
        usernameBox.sendKeys(ispravanUsername);

        WebElement continueButton = driver.findElement(By.cssSelector("#primary > div > main > div.wp-login__container > div > form > div.card.login__form > div.login__form-action > button"));
        continueButton.click();

        String ispravanPassword = "mummify-backstab-blinks";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#password")));
        WebElement passwordBox = driver.findElement(By.cssSelector("input#password"));
        passwordBox.sendKeys(ispravanPassword);

        WebElement logInButton2 = driver.findElement(By.cssSelector("#primary > div > main > div.wp-login__container > div > form > div.card.login__form > div.login__form-action > button"));
        logInButton2.click();

        Assert.assertFalse(driver.findElements(By.cssSelector("#header > div.masterbar__section.masterbar__section--right > a.masterbar__item.masterbar__item-me > span > img")).isEmpty());

        driver.quit();

    }
}
