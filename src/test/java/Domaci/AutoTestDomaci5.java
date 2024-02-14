package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class AutoTestDomaci5 {

    /*Domaci Zadatak 2:
    Otici na sajt Herkouapp(https://the-internet.herokuapp.com/) i napisati sto vise test case-eva (Vas izbor sta cete testirati).*/

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor jse;
    Random r = new Random();

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void pageSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        jse = (JavascriptExecutor)driver;

        driver.get("https://the-internet.herokuapp.com/");
    }

    @Test(priority = 10)
    public void addRemoveElements(){
        WebElement addRemoveElementsLink = driver.findElement(By.linkText("Add/Remove Elements"));
        jse.executeScript("arguments[0].scrollIntoView()", addRemoveElementsLink);
        addRemoveElementsLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addElementButton = driver.findElement(By.xpath("//button[contains(text(), 'Add Element')]"));
        int x = r.nextInt(15)+1;
        for(int i = 0; i<x; i++)
            addElementButton.click();

        Assert.assertTrue(driver.findElement(By.className("added-manually")).isDisplayed());
        ArrayList<WebElement> deleteButtons = (ArrayList<WebElement>) driver.findElements(By.className("added-manually"));
        Assert.assertEquals(x, deleteButtons.size());

        for(WebElement pivot:deleteButtons)
            pivot.click();

        /*elements = (ArrayList<WebElement>) driver.findElements(By.className("added-manually"));
        Assert.assertEquals(0, elements.size());*/

        Boolean buttonIsPresent = false;
        try{
            buttonIsPresent = driver.findElement(By.className("added-manually")).isDisplayed();
        }catch (Exception e){

        }
        Assert.assertFalse(buttonIsPresent);
    }

    @Test
    public void checkboxes() throws InterruptedException {
        WebElement checkboxesLink = driver.findElement(By.linkText("Checkboxes"));
        jse.executeScript("arguments[0].scrollIntoView()", checkboxesLink);
        checkboxesLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/checkboxes");

        ArrayList<WebElement> checkboxes = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[type=\"checkbox\"]"));
        Boolean checked;
        for(WebElement pivot:checkboxes){
            checked = pivot.isSelected();
            pivot.click();
            Assert.assertEquals(pivot.isSelected(), !checked);
            pivot.click();
            Assert.assertEquals(pivot.isSelected(), checked);
        }
    }

    @Test(priority = 30)
    public void multipleWindows() throws InterruptedException {
        WebElement windowsLink = driver.findElement(By.linkText("Multiple Windows"));
        jse.executeScript("arguments[0].scrollIntoView()", windowsLink);
        windowsLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows");

        WebElement newWindow = driver.findElement(By.linkText("Click Here"));

        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());

        int x = r.nextInt(15)+1;
        for(int i=0; i<x; i++){
            newWindow.click();
            ArrayList<String> temp = new ArrayList<>(driver.getWindowHandles());
            if(!temp.get(i+1).equals(tabList.get(0))){
                driver.switchTo().window(temp.get(i+1));
                tabList.add(driver.getWindowHandle());
                driver.switchTo().window(tabList.get(0));
            }
        }

        Assert.assertEquals(tabList.size(), x+1);

    }

    @AfterMethod
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }



}
