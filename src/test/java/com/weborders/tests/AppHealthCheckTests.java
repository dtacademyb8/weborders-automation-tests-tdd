package com.weborders.tests;


import com.weborders.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AppHealthCheckTests extends TestBase{


    @Test(groups = {"homepage", "smoke", "flaky"})
    public void verifyTitleTest() throws InterruptedException {

        driver.get(ConfigReader.getProperty("url"));
        Thread.sleep(1000);
        Assert.assertEquals(driver.getTitle(), "Web Orders");


    }


    @Test (groups =  {"smoke"} )
    public void verifyURLTest(){
        driver.get(ConfigReader.getProperty("url"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

    }

}
