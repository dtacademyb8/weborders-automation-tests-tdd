package com.weborders.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.weborders.utilities.ConfigReader;
import com.weborders.utilities.Driver;
import com.weborders.utilities.SeleniumUtils;
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


            extentReports.setSystemInfo("Name", "Web Orders Automated Tests");
            extentReports.setSystemInfo("Environment", "QA");
            extentReports.setSystemInfo("SDET", "Johnny Cash");
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extentReports.setSystemInfo("Homepage", ConfigReader.getProperty("url"));

    }


    @BeforeMethod (alwaysRun = true)
    public void setupMethod(Method method){


        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        logger = extentReports.createTest(method.getName());  // create the logger object
    }


    @AfterMethod (alwaysRun = true)
    public void tearDownMethod(ITestResult testResult){  //ITestResult is a listener interface that is used to grab the test result

        if(testResult.getStatus() == ITestResult.SUCCESS){
            logger.pass("Test Passed: " + testResult.getName());
        }else if(testResult.getStatus() == ITestResult.SKIP){
            logger.skip("Test Skipped: " + testResult.getName());
        }else if (testResult.getStatus() == ITestResult.FAILURE){
            logger.fail("Test failed: " + testResult.getName());
            logger.fail(testResult.getThrowable());
            String screenshotFilePath = SeleniumUtils.getScreenshot("failed");
            logger.addScreenCaptureFromPath(screenshotFilePath);
        }


        Driver.quitDriver();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport(){
           extentReports.flush();
    }

}
