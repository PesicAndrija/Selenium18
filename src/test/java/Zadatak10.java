import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Zadatak10 {
    public static void main(String[] args) throws InterruptedException, IOException {

        //Zadatak 10
        //Otici na stranicu https://imgflip.com/memegenerator
        //Uploadovati sliku od koje treba napraviti mim
        //Mim mora biti vezan za IT, QA ili kurs
        //Sliku rucno skinuti i ubaciti na Slack thread poruku
        //Autor mima sa najvise lajkova dobija pivo na druzenju
        //
        //Napomena: Izazov zadatka je kod lokatora, assertove ne treba dodavati

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://imgflip.com/memegenerator");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement uploadTemplateButton = driver.findElement(By.id("mm-show-upload"));
        uploadTemplateButton.click();

        WebElement uploadImageButton = driver.findElement(By.id("mm-upload-file"));
        String imageLocation = "D:\\Users\\ANDRA\\Desktop\\squidward.jpg";
        uploadImageButton.sendKeys(imageLocation);

        WebElement uploadButton = driver.findElement(By.id("mm-upload-btn"));
        uploadButton.click();

        WebElement bottomText = driver.findElement(By.cssSelector("textarea[placeholder=\"Bottom Text\"]"));
        bottomText.sendKeys("your free time during the course");

        WebElement generateMemeButton = driver.findElement(By.cssSelector(".mm-generate.b.but"));
        generateMemeButton.click();


        //-----------------------------------

        //Save image
        WebElement meme = driver.findElement(By.id("done-img"));
        wait.until(ExpectedConditions.attributeContains(meme, "src", "imgflip.com"));
        String link = meme.getAttribute("src");
        URL imageURL = new URL(link);
        BufferedImage saveImage = ImageIO.read(imageURL);
        ImageIO.write(saveImage, "png", new File("D:\\Users\\ANDRA\\Desktop\\meme.png"));

        driver.quit();

    }
}