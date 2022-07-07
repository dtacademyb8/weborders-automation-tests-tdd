package com.weborders.tests;

import com.weborders.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class TestBase {

    public WebDriver driver;


    @BeforeMethod (alwaysRun = true)
    public void setupMethod(){

        String browser = ConfigReader.getProperty("browser");

        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver =  new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver =  new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver =  new EdgeDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver =  new SafariDriver();
                break;
            default:
                 throw new RuntimeException("Invalid browser");
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

    }


    @AfterMethod (alwaysRun = true)
    public void tearDownMethod(){

        driver.quit();

    }

}
