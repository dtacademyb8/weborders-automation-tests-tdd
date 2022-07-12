package com.weborders.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    private static  WebDriver driver;

    private Driver(){}


    public static WebDriver getDriver(){

        if(driver == null ){

            String browser = System.getProperty("browser");  //read the browser type from command line, if no browser is passed value returned will be null

            if(browser == null){ // if no browser is passed thru command line
                browser = ConfigReader.getProperty("browser"); // Read the browser type from .properties file
            }


            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver =  new ChromeDriver();
                    break;
                case "chrome_headless":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    WebDriverManager.chromedriver().setup();
                    driver =  new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver =  new FirefoxDriver();
                    break;
                case "firefox_headless":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    WebDriverManager.firefoxdriver().setup();
                    driver =  new FirefoxDriver(firefoxOptions);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver =  new EdgeDriver();
                    break;
                case "edge_headless":
                    EdgeOptions edgeOptions =  new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    WebDriverManager.edgedriver().setup();
                    driver =  new EdgeDriver(edgeOptions);
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
