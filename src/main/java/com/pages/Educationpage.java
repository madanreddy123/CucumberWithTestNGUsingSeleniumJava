package com.pages;

import com.base.ActionClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Educationpage {

    WebDriver driver;
    ActionClass actions;


    By Primary_School = By.xpath("//input[@placeholder='Primary School Name']");
    By Secondary_School = By.xpath("//input[@placeholder='Secondary School Name']");
    By Intermediate = By.xpath("//input[@placeholder='Intermediate College Name']");
    By Engineering = By.xpath("//input[@placeholder='Engineering College Name']");
    By University = By.xpath("//input[@placeholder='University Name']");
    By Intermediate_Percentage = By.xpath("//input[@placeholder='Intermediate Percentage']");
    By Gender = By.xpath("//input[@id='profession-0']");

    public Educationpage(WebDriver driver) {

        this.driver = driver;
        actions = new ActionClass(driver);
    }

    //Parameterizing the void functions

    public void Primary_School(String primary_School)

    {
        actions.waitForVisibilityOfElement(Primary_School, 15);
        actions.sendKeys(Primary_School, primary_School);

    }

    public  void Secondary_School(String secondary_School) throws InterruptedException

    {
        actions.waitForVisibilityOfElement(Secondary_School, 15);
        actions.sendKeys(Secondary_School, secondary_School);


    }

    public  void Intermediate(String intermediate)

    {

        driver.findElement(Intermediate).sendKeys(intermediate);


    }
    public  void Engineering(String engineering) throws InterruptedException
    {

        driver.findElement(Engineering).sendKeys(engineering);

    }
    public  void University(String university) throws InterruptedException

    {
        driver.findElement(University).sendKeys(university);


    }

    public  void Intermediate_Percentage(String Percentage) throws InterruptedException

    {
        driver.findElement(Intermediate_Percentage).click();
        driver.findElement(Intermediate_Percentage).sendKeys(Percentage);
        driver.findElement(Intermediate_Percentage).sendKeys(Keys.ENTER);
        Thread.sleep(4000);


    }


    public  void Gender(String gender) throws InterruptedException

    {
        driver.findElement(Gender).click();


    }

    public void launchurl() {

        driver.get("https://testzenlabs.ie/education-form/");
    }
    By uploadfile = By.xpath("//input[@type = 'file']");

    public void uploadAFileAndValidateIfItsDownloaded() {

        actions.waitforSeconds(2);
        actions.waitForVisibilityOfElement(uploadfile, 50);
        actions.sendKeys(uploadfile, "/Downloads/TestData/sample.xlsx");
    }

}
