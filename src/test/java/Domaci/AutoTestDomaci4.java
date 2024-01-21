package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoTestDomaci4 {

   /* Koristeci Anotacije - Ulogujte se na demoqa(https://demoqa.com/ -> Book Store Application) preko cookies-a, dodati dve knjige na svoj nalog, zatim se izlogovati brisanjem cookies-a.
     Ulogovati se ponovo preko log-in forme i potvrditi da se knjige i dalje nalaze na nalogu*/

    WebDriver driver;
    WebDriverWait wait;
    String validUsername, validPassword, loginURL, booksURL, profileURL;
    ArrayList<String> nasloviKnjiga = new ArrayList<>();
    Cookie expires, userID, userName, token;
    Random r = new Random();
    JavascriptExecutor jse;
    WebElement logInButton1, usernameField, passwordField, logInButton2, logOutButton, addBook;
    List<WebElement> knjige;
    int k1, k2;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        validUsername = "pera";
        validPassword = "Lozinka123$";

        loginURL = "https://demoqa.com/login";
        booksURL = "https://demoqa.com/books";
        profileURL = "https://demoqa.com/profile";

        driver.get("https://demoqa.com/");

        WebElement bookStoreAppButton = driver.findElement(By.xpath("//h5[contains(text(), 'Book Store Application')]"));
        jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", bookStoreAppButton);
        bookStoreAppButton.click();

        //Uzimanje svežih kolačića

        logInButton1 = driver.findElement(By.id("login"));
        logInButton1.click();

        Assert.assertEquals(loginURL, driver.getCurrentUrl());

        usernameField = driver.findElement(By.id("userName"));
        passwordField = driver.findElement(By.id("password"));
        logInButton2 = driver.findElement(By.id("login"));

        usernameField.sendKeys(validUsername);
        passwordField.sendKeys(validPassword);
        logInButton2.click();

        logOutButton = driver.findElement(By.xpath("//button[contains(text(), 'Log out')]"));

        Assert.assertEquals(validUsername, driver.findElement(By.id("userName-value")).getText());
        Assert.assertTrue(logOutButton.isDisplayed());

        expires = driver.manage().getCookieNamed("expires");
        userID = driver.manage().getCookieNamed("userID");
        userName = driver.manage().getCookieNamed("userName");
        token = driver.manage().getCookieNamed("token");

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        logInButton1 = driver.findElement(By.id("login"));
        Assert.assertTrue(logInButton1.isDisplayed());
    }

    @Test(priority = 10)
    public void userLogInSuccessfullyWithCookies(){

        driver.manage().addCookie(expires);
        driver.manage().addCookie(userID);
        driver.manage().addCookie(userName);
        driver.manage().addCookie(token);
        driver.navigate().refresh();

        logOutButton = driver.findElement(By.xpath("//button[contains(text(), 'Log out')]"));

        Assert.assertEquals(validUsername, driver.findElement(By.id("userName-value")).getText());
        Assert.assertTrue(logOutButton.isDisplayed());

    }

    @Test(priority = 20)
    public void addTwoRandomBooks(){

        knjige = driver.findElements(By.cssSelector("span.mr-2 > a"));
        k1 = r.nextInt(knjige.size());
        k2 = r.nextInt(knjige.size());
        while(k1==k2) k2 = r.nextInt(knjige.size());
        int[] dveKnjige = {k1, k2};

        for(int i=0; i<2; i++){
            nasloviKnjiga.add(knjige.get(dveKnjige[i]).getText());
            jse.executeScript("arguments[0].scrollIntoView()", knjige.get(dveKnjige[i]));
            knjige.get(dveKnjige[i]).click();
            addBook = driver.findElement(By.xpath("//button[contains(text(), 'Add To Your Collection')]"));
            addBook.click();
            driver.navigate().back();
            knjige = driver.findElements(By.cssSelector("span.mr-2 > a"));
        }

    }

    @Test(priority = 30)
    public void logOutSuccessfullyByDeletingCookies(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        logInButton1 = driver.findElement(By.id("login"));
        Assert.assertTrue(logInButton1.isDisplayed());
    }

    @Test(priority = 40)
    public void LogInSuccessfullyWithValidCredentials(){
        logInButton1 = driver.findElement(By.id("login"));
        logInButton1.click();

        Assert.assertEquals(loginURL, driver.getCurrentUrl());

        usernameField = driver.findElement(By.id("userName"));
        passwordField = driver.findElement(By.id("password"));
        logInButton2 = driver.findElement(By.id("login"));

        usernameField.sendKeys(validUsername);
        passwordField.sendKeys(validPassword);
        logInButton2.click();

        logOutButton = driver.findElement(By.xpath("//button[contains(text(), 'Log out')]"));

        Assert.assertEquals(validUsername, driver.findElement(By.id("userName-value")).getText());
        Assert.assertTrue(logOutButton.isDisplayed());


    }

    @Test(priority = 50)
    public void checkIfBooksAreStillThere(){
        WebElement profileLink = driver.findElement(By.xpath("//span[contains(text(), 'Profile')]"));
        jse.executeScript("arguments[0].scrollIntoView()", profileLink);
        profileLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), profileURL);

        for(String pivot:nasloviKnjiga)
            Assert.assertTrue(driver.findElement(By.linkText(pivot)).isDisplayed());

    }

}
