import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium6 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wordpress.com/");

        Cookie cookie = new Cookie("wordpress_logged_in","salvage4389%7C1800037961%7C2XHzYEowdBS9a7HUd7OGnLQc8Eb6KxYl3HLfM6Bu4Go%7C61a836ef30382111196a8cce59e0e01d176ee38665f1bf63188f25915a79ca6f");

        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        Thread.sleep(10000);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

    }
}
