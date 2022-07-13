package com.weborders.tests;

import com.sun.javafx.geom.Edge;
import com.weborders.pages.HomePage;
import com.weborders.pages.LoginPage;
import com.weborders.pages.ProductsPage;
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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class LoginTests extends TestBase {



    @Test(groups =  {"smoke"} )
    public void testLoginWithValidCredentials() throws IOException {



//        FileInputStream fis =  new FileInputStream("config.properties");
//        Properties properties = new Properties();
//        properties.load(fis);
//        fis.close();

        logger.info("Initializing the browser. Navigating to a url");
        driver.get(ConfigReader.getProperty("url"));

        logger.info("Entering valid credentials");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(ConfigReader.getProperty("username"), Keys.TAB, ConfigReader.getProperty("password"), Keys.ENTER);


        logger.info("Clicking on products link");
        driver.findElement(By.linkText("View all product")).click();

//        SeleniumUtils.waitForVisibility(By.xpath("cdsd"), 4);
        List<WebElement> elements = driver.findElements(By.xpath("//table[@class='ProductsTable']//th"));


        List<String> elementsText = SeleniumUtils.getElementsText(elements);


        logger.info("Asserting the title");
        Assert.assertEquals(driver.getTitle(), "Web Orders");





    }

    @Test(groups =  {"smoke"} )
    public void testHeaderValues() throws IOException {



//


        driver.get(ConfigReader.getProperty("url"));

        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidCredentials();


        HomePage homepage = new HomePage();
        homepage.allProductsLink.click();


        ProductsPage productsPage = new ProductsPage();

        SeleniumUtils.waitForVisibilityOfMultipleElementsAsList(productsPage.tableHeaders, 5);



        List<String> elementsText = SeleniumUtils.getElementsText(productsPage.tableHeaders);



        Assert.assertEquals(elementsText, Arrays.asList("Product name" , "Price", "Discount"));



    }


    @Test (groups = {"homepage", "flaky"})
    public void testLoginWithInValidCredentials(){

        Driver.getDriver().navigate().to(ConfigReader.getProperty("url"));

       LoginPage loginPage =  new LoginPage();

       loginPage.username.sendKeys("Heloo", Keys.TAB, "bdcnbsgh", Keys.ENTER);



        Assert.assertTrue(loginPage.errorMessage.isDisplayed());

    }

    @Test(groups =  {"smoke"} )
    public void testCaseForDemo() throws IOException {


        Assert.assertEquals(true, true);



    }
    
      @Test(groups =  {"smoke"} )
    public void testCaseForDemo2() throws IOException {


        Assert.assertEquals(true, true);



    }



}
