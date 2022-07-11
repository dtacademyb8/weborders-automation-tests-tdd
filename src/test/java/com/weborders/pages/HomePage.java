package com.weborders.pages;

import com.weborders.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public  HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(linkText = "View all products")   //Page Factory
    public WebElement allProductsLink;
}
