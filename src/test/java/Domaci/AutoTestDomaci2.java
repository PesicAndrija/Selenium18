package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AutoTestDomaci2 {
    public static void main(String[] args) throws InterruptedException {

        //Domaci Zadatak 2:
        //https://demoqa.com/text-box napisati test case za dati text box

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");

        WebElement fullNameBox = driver.findElement(By.cssSelector("input#userName"));
        WebElement emailBox = driver.findElement(By.cssSelector("input#userEmail"));
        WebElement currentAddressBox = driver.findElement(By.cssSelector("textarea#currentAddress"));
        WebElement permanentAddressBox = driver.findElement(By.cssSelector("textarea#permanentAddress"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        //Testiranje svakog polja pojedinačno i svih polja odjednom sa ispravnim unosom.

        String ime = "Petar Petrović";
        fullNameBox.sendKeys(ime);
        submitButton.click();
        WebElement nameOutput = driver.findElement(By.cssSelector("p#name"));
        Assert.assertTrue(nameOutput.isDisplayed() && nameOutput.getText().contains(ime));

        fullNameBox.clear();
        submitButton.click();

        String ispravanEmail = "petar.petrovic@gmail.com";
        emailBox.sendKeys(ispravanEmail);
        submitButton.click();
        WebElement emailOutput = driver.findElement(By.cssSelector("p#email"));
        Assert.assertTrue(emailOutput.isDisplayed() && emailOutput.getText().contains(ispravanEmail));

        emailBox.clear();
        submitButton.click();

        String trenutnaAdresa = "Kej Oslobođenja 19, Beograd 11080";
        currentAddressBox.sendKeys(trenutnaAdresa);
        submitButton.click();
        WebElement currentAddressOutput = driver.findElement(By.cssSelector("p#currentAddress"));
        Assert.assertTrue(currentAddressOutput.isDisplayed() && currentAddressOutput.getText().contains(trenutnaAdresa));

        currentAddressBox.clear();
        submitButton.click();

        String prijavljenaAdresa = "Vinarska 17, Beograd 11080";
        permanentAddressBox.sendKeys(prijavljenaAdresa);
        submitButton.click();
        WebElement permanentAddressOutput = driver.findElement(By.cssSelector("p#permanentAddress"));
        Assert.assertTrue(permanentAddressOutput.isDisplayed() && permanentAddressOutput.getText().contains(prijavljenaAdresa));

        permanentAddressBox.clear();
        submitButton.click();

        fullNameBox.sendKeys(ime);
        emailBox.sendKeys(ispravanEmail);
        currentAddressBox.sendKeys(trenutnaAdresa);
        permanentAddressBox.sendKeys(prijavljenaAdresa);
        submitButton.click();
        nameOutput = driver.findElement(By.cssSelector("p#name"));
        emailOutput = driver.findElement(By.cssSelector("p#email"));
        currentAddressOutput = driver.findElement(By.cssSelector("p#currentAddress"));
        permanentAddressOutput = driver.findElement(By.cssSelector("p#permanentAddress"));

        Assert.assertTrue(nameOutput.isDisplayed() && nameOutput.getText().contains(ime));
        Assert.assertTrue(emailOutput.isDisplayed() && emailOutput.getText().contains(ispravanEmail));
        Assert.assertTrue(currentAddressOutput.isDisplayed() && currentAddressOutput.getText().contains(trenutnaAdresa));
        Assert.assertTrue(permanentAddressOutput.isDisplayed() && permanentAddressOutput.getText().contains(prijavljenaAdresa));

        //Testiranje svih polja pojedinačno sa neispravnim unosom sem za email polje.

        String neispravanEmail = "petrovic.petar$hotmail.com";

        fullNameBox.clear();
        emailBox.clear();
        currentAddressBox.clear();
        permanentAddressBox.clear();
        submitButton.click();

        fullNameBox.sendKeys(ime);
        emailBox.sendKeys(neispravanEmail);
        currentAddressBox.sendKeys(trenutnaAdresa);
        permanentAddressBox.sendKeys(prijavljenaAdresa);
        submitButton.click();

        Assert.assertTrue(driver.findElements(By.cssSelector("p#name")).isEmpty());
        Assert.assertTrue(driver.findElements(By.cssSelector("p#email")).isEmpty());
        Assert.assertTrue(driver.findElements(By.cssSelector("p#currentAddress")).isEmpty());
        Assert.assertTrue(driver.findElements(By.cssSelector("p#permanentAddress")).isEmpty());

        System.out.println("Test je uspešan!");
        driver.quit();

    }
}
