package com.weborders.tests;

import com.weborders.utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

        Assert.assertEquals(driver.getTitle(), "Web Orders");

    }


    @Test (groups = {"homepage", "flaky"})
    public void testLoginWithInValidCredentials(){
        driver.get(ConfigReader.getProperty("url"));

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Heloo", Keys.TAB, "bdcnbsgh", Keys.ENTER);


        WebElement errorMessage = driver.findElement(By.id("ctl00_MainContent_status"));

        Assert.assertTrue(errorMessage.isDisplayed());

    }
}
