package com.weborders.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    private static  WebDriver driver;

    private Driver(){}


    public static WebDriver getDriver(){

        if(driver == null ){

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

        }

        return driver;

    }



    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }

    }


}
