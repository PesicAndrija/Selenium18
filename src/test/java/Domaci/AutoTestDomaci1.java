package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.ENTER;

public class AutoTestDomaci1 {
    public static void main(String[] args) {

        /*Domaci Zadatak:
        Otici na Google, zatim preko browser-a otici na YouTube i pustiti neku pesmu po Vasem izboru. */

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //WebDriverManager.firefoxdriver().setup();
        //WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        //Kada koristim CSS slektore uvek uspešno klinke na ono što treba, sa XPath selektorima retko kada radi.

        //driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/div/textarea")).sendKeys("YouTube"+ENTER);
        driver.findElement(By.cssSelector("#APjFqb")).sendKeys("YouTube"+ENTER);

        //driver.findElement(By.xpath("/html/body/div[5]/div/div[9]/div[1]/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div/span/a")).click();
        driver.findElement(By.cssSelector("#rso > div.hlcw0c > div > div > div > div > div > div > div > div.yuRUbf > div > span > a")).click();

        //Iz nekog razloga prvo moram da kliknem na polje za pretragu pa tek onda da unesem tekst.
        //driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/form/div[1]/div[1]/input")).click();
        //driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/form/div[1]/div[1]/input")).sendKeys("First Aid Kit The Lions Roar"+ENTER);
        driver.findElement(By.cssSelector("input#search")).click();
        driver.findElement(By.cssSelector("input#search")).sendKeys("First Aid Kit The Lions Roar"+ENTER);

        //Kada direktno pristupi stranici sa rezultatima pretrage uspešno klikne na prvi video, bilo da koristim xpath ili css selektor.
        //Ali kada korak po korak pristupi istoj stranici sa rezultatima pretrage ne može da klikne na prvi video koji god selektor da koristim.
        //Proverio sam da li se selektori razlikuju u odnsosu na to kako smo došli do stranice, ali ne razlikuju se potpuno su isti.
        //driver.manage().window().maximize();
        //driver.get("https://www.youtube.com/results?search_query=First+Aid+Kit+The+Lions+Roar");

        //Kada dodam ovu kratku pauzu radi svaki put kako treba (tj tako izgleda)
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //driver.findElement(By.xpath("/html/body/ytd-app/div[1]/ytd-page-manager/ytd-search/div[1]/ytd-two-column-search-results-renderer/div/ytd-section-list-renderer/div[2]/ytd-item-section-renderer/div[3]/ytd-video-renderer[1]/div[1]/div/div[1]/div/h3/a/yt-formatted-string")).click();
        driver.findElement(By.cssSelector("#video-title > yt-formatted-string")).click();

    }
}
