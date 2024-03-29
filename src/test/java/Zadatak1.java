import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Zadatak1 {
    public static void main(String[] args) {

        //Zadatak 1
        //Pokrenite browser, idite na Linkedin sajt, uradite refresh, idite na Joberty sajt
        //vratite se nazad i odstampajte poslednji URL na kom se nalazite
        //na kraju zatvorite driver

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //WebDriverManager.firefoxdriver().setup();
        //WebDriver driver = new FirefoxDriver();

        driver.navigate().to("https://www.linkedin.com");
        driver.navigate().refresh();
        driver.get("https://www.joberty.com");
        driver.navigate().back();
        System.out.println(driver.getCurrentUrl());
        driver.quit();

    }
}
