import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.Keys.ENTER;

public class Selenium4 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea"));
        searchBox.sendKeys("ITBootcamp"+ENTER);
        driver.navigate().back();
        searchBox.sendKeys("Wikipedia"+ENTER);

        // LOKATORI / SELEKTORI

        // ID - PRvi po hijerarhiji, u idealnoj situaciji je jedinstven (ne mora biti jedinstven), najstabilniji za lokatore.
        WebElement idSearchBox = driver.findElement(By.id("APjFqb"));

        // Name
        WebElement nameSearchBox = driver.findElement(By.name("q"));

        // Class
        WebElement classSearchBox = driver.findElement(By.className("gLFyf"));

        // CSS selector
        WebElement cssSearchBox = driver.findElement(By.cssSelector("textarea[type='search']"));

        // Relative XPath
        WebElement relativeXPathSearchBox = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));

        // Absolute XPathSearchBox - Poslednji po hijerarhiji, najnestabilniji, može se koristiti na početku dok se kreiraju
        WebElement absolutXPath = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea"));

    }
}
