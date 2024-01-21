import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class Zadatak2 {
    public static void main(String[] args) {
        //Zadatak 2
        //Otvoriti browser i jos 5 tabova
        //Na svakom tabu otvoriti URL po zelji
        //Zatvoriti sve tabove osim onog gde je otvoren Google

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        js.executeScript("window.open()");
        js.executeScript("window.open()");

        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(listaTabova.get(0));
        driver.get("https://www.reddit.com/");
        driver.switchTo().window(listaTabova.get(1));
        driver.get("https://www.youtube.com/");
        driver.switchTo().window(listaTabova.get(2));
        driver.get("https://www.google.com/");
        driver.switchTo().window(listaTabova.get(3));
        driver.get("https://www.linkedin.com/");
        driver.switchTo().window(listaTabova.get(4));
        driver.get("https://www.joberty.com/");
        driver.switchTo().window(listaTabova.get(5));
        driver.get("https://weboasis.app/");

        for(int i=listaTabova.size()-1; i>=0; i--){
            if(driver.switchTo().window(listaTabova.get(i)).getCurrentUrl().equalsIgnoreCase("https://www.google.com/"))
                continue;
            driver.switchTo().window(listaTabova.get(i));
            driver.close();
        }
    }
}
