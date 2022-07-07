package com.weborders.tests;

import com.weborders.utilities.ConfigReader;
import com.weborders.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.time.Duration;

public abstract class TestBase {

    public WebDriver driver;


    @BeforeMethod (alwaysRun = true)
    public void setupMethod(){


        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

    }


    @AfterMethod (alwaysRun = true)
    public void tearDownMethod(){

        Driver.quitDriver();

    }

}
