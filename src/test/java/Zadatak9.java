import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.Cookie;

import java.time.Duration;

public class Zadatak9 {
    public static void main(String[] args) throws InterruptedException {

        /*Zadatak 9
        https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
        Testirati dodavanje knjige u korpu i da li se knjiga obrise kada obrisete kolacice*/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        Thread.sleep(10000);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement addToCartButton = driver.findElement(By.cssSelector("input#add-to-cart-button"));
        addToCartButton.click();



        WebElement cartButton = driver.findElement(By.cssSelector("a#nav-cart"));
        cartButton.click();

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        Assert.assertTrue(driver.findElements(By.cssSelector("div[data-asin=\"1788473574\"]")).isEmpty());

        WebElement cartCount = driver.findElement(By.cssSelector("span#nav-cart-count"));
        Assert.assertEquals(cartCount.getText(), "0");

        WebElement prazan = driver.findElement(By.cssSelector(".a-row.sc-your-amazon-cart-is-empty"));
        Assert.assertTrue(prazan.getText().contains("Your Amazon Cart is empty"));

        driver.quit();

    }
}
