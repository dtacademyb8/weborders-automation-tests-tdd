package com.weborders.tests;

import com.sun.javafx.geom.Edge;
import com.weborders.utilities.ConfigReader;
import com.weborders.utilities.Driver;
import com.weborders.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class LoginTests extends TestBase {



    @Test(groups =  {"smoke"} )
    public void testLoginWithValidCredentials() throws IOException {



//        FileInputStream fis =  new FileInputStream("config.properties");
//        Properties properties = new Properties();
//        properties.load(fis);
//        fis.close();


        driver.get(ConfigReader.getProperty("url"));

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(ConfigReader.getProperty("username"), Keys.TAB, ConfigReader.getProperty("password"), Keys.ENTER);


        driver.findElement(By.linkText("View all products")).click();

        SeleniumUtils.waitForVisibility(By.xpath("cdsd"), 4);
        List<WebElement> elements = driver.findElements(By.xpath("//table[@class='ProductsTable']//th"));


        List<String> elementsText = SeleniumUtils.getElementsText(elements);

        System.out.println(elementsText);

        Assert.assertEquals(driver.getTitle(), "Web Orders");



    }


    @Test (groups = {"homepage", "flaky"})
    public void testLoginWithInValidCredentials(){

        Driver.getDriver().navigate().to(ConfigReader.getProperty("url"));

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Heloo", Keys.TAB, "bdcnbsgh", Keys.ENTER);


        WebElement errorMessage = driver.findElement(By.id("ctl00_MainContent_status"));

        Assert.assertTrue(errorMessage.isDisplayed());

    }



}
