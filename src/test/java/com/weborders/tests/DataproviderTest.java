package com.weborders.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataproviderTest extends TestBase{


    @Test(dataProvider = "usernamePassCombo")

    public void testLoginWithInValidCredentials(String username, String password){
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(username, Keys.TAB, password, Keys.ENTER);


        WebElement errorMessage = driver.findElement(By.id("ctl00_MainContent_status"));

        Assert.assertTrue(errorMessage.isDisplayed());

    }


    @DataProvider(name = "usernamePassCombo", parallel = true)
    public Object[][] getData(){

        return new Object[][]{
                {"afa", "dscsjdchvdhs"},
                {"majd", "sdcdscds"},
                {"amine", "vfbfdbfdbfd"},
                {"lara", "cdsc"},
                {"afa", "dscsjdchvdhs"},
                {"majd", "sdcdscds"},
                {"amine", "vfbfdbfdbfd"},
                {"lara", "cdsc"}
        };
    }
}
