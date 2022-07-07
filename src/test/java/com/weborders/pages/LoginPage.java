package com.weborders.pages;

import com.weborders.utilities.ConfigReader;
import com.weborders.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this); // This will intialize all variables marked with @Find

    }

//    public WebElement username = Driver.getDriver().findElement(By.id("ctl00_MainContent_username"));  // Non Page Factory Page Object Model

    @FindBy(id = "ctl00_MainContent_username")   //Page Object Model combined with Page Factory
    public WebElement username;

    @FindBy(xpath = "//span[@id='ctl00_MainContent_status']")
    public WebElement errorMessage;


    public void loginWithValidCredentials(){
        username.sendKeys(ConfigReader.getProperty("username"), Keys.TAB, ConfigReader.getProperty("password"), Keys.ENTER);
    }


}
