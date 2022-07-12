package com.weborders.tests;

import com.weborders.pages.HomePage;
import com.weborders.pages.LoginPage;
import com.weborders.pages.ProductsPage;
import com.weborders.utilities.ConfigReader;
import com.weborders.utilities.Driver;
import com.weborders.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Login extends TestBase {



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

    @Test(groups =  {"smoke"} )
    public void testHeaderValues() throws IOException {






        driver.get(ConfigReader.getProperty("url"));

        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidCredentials();


        HomePage homepage = new HomePage();
        homepage.allProductsLink.click();


        ProductsPage productsPage = new ProductsPage();

        SeleniumUtils.waitForVisibilityOfMultipleElementsAsList(productsPage.tableHeaders, 5);



        List<String> elementsText = SeleniumUtils.getElementsText(productsPage.tableHeaders);


        throw new SkipException("Test Skipped"); // to explicitly mark the test result as SKIPPED



//        Assert.assertEquals(elementsText, Arrays.asList("Product name" , "Price", "Discount"));



    }


    @Test (groups = {"homepage", "flaky"})
    public void testLoginWithInValidCredentials(){

        Driver.getDriver().navigate().to(ConfigReader.getProperty("url"));

       LoginPage loginPage =  new LoginPage();

       loginPage.username.sendKeys("Heloo", Keys.TAB, "bdcnbsgh", Keys.ENTER);



        Assert.assertTrue(loginPage.errorMessage.isDisplayed());

    }



}
