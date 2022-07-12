package com.weborders.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.weborders.utilities.ConfigReader;
import com.weborders.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class TestBase {



    protected WebDriver driver;
    protected static ExtentReports extentReports;  // manages the report generation
    protected static ExtentSparkReporter htmlReport; // creates the html report file
    protected static ExtentTest logger; // manages the individual test steps and logs
    @BeforeSuite(alwaysRun = true)
    public void setupReport(){
          extentReports =  new ExtentReports();
          String pathToReportFile =   System.getProperty("user.dir")+"/target/extentReports/index.html";
          htmlReport = new ExtentSparkReporter(pathToReportFile);
          extentReports.attachReporter(htmlReport);

    }


    @BeforeMethod (alwaysRun = true)
    public void setupMethod(Method method){


        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        logger = extentReports.createTest(method.getName());
    }


    @AfterMethod (alwaysRun = true)
    public void tearDownMethod(ITestResult testResult){  //ITestResult is a listener interface that is used to grab the test result

        if(testResult.getStatus() == ITestResult.SUCCESS){
            logger.pass("Test Passed");
        }else if(testResult.getStatus() == ITestResult.SKIP){
            logger.skip("Test Skipped");
        }else if (testResult.getStatus() == ITestResult.FAILURE){
            logger.fail("Test failed.");
        }


        Driver.quitDriver();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport(){
           extentReports.flush();
    }

}
