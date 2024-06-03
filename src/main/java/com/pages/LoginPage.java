package com.pages;

import com.base.ActionClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
	
	 WebDriver driver;

	
	 By Firstname = By.xpath("//input[@placeholder='Your Name']");
	 By Middle_Name = By.xpath("//input[@placeholder='Your Middle Name']");
	 By Lastname = By.xpath("//input[@placeholder='Your Last Name']");
	 By Phone_number = By.xpath("//input[@placeholder='Phone Number']");
	 By Address_One = By.xpath("//input[@placeholder='Your Address']");
	 By Address_Two = By.xpath("//input[@placeholder='Your Address Two']");
	 By Pin_code = By.xpath("//input[@placeholder='Your Pin Code']");
	 By Continent = By.xpath("//input[@placeholder='Asia']");

	 ActionClass actions;
	 public LoginPage(WebDriver driver) {
		 this.driver = driver;
		 actions = new ActionClass(driver);
	}

		//Parameterizing the void functions
		
	 public void Firstname(String first_name)
		 
		 {

			 actions.waitForVisibilityOfElement(Firstname, 15);
			 actions.sendKeys(Firstname, first_name);

			 
		    }
		
		public void Middle_Name(String middle_Name)  
		 
		 {

			 actions.waitForVisibilityOfElement(Middle_Name, 15);
			 actions.sendKeys(Middle_Name, middle_Name);


			 
		    }
		
	public void Lastname(String last_Name)
	{
		
		actions.waitForVisibilityOfElement(Lastname, 15);
		actions.sendKeys(Lastname, last_Name);
	}
	
	public  void Phone_number(String phone_number) {
		
		actions.waitForVisibilityOfElement(Phone_number, 15);
		actions.sendKeys(Phone_number, phone_number);
		}
	
	
	public  void Address_One(String address_One) {

		actions.waitForVisibilityOfElement(Address_One, 15);
		actions.sendKeys(Address_One, address_One);
		}
	
	public  void Address_Two(String address_Two) {
		actions.waitForVisibilityOfElement(Address_Two, 15);
		actions.sendKeys(Address_Two, address_Two);
			}
	
public  void Pin_code(String pin_code) {

	actions.waitForVisibilityOfElement(Pin_code, 15);
	actions.sendKeys(Pin_code, pin_code);
			}

public  void Continent(String continent) {

	actions.waitForVisibilityOfElement(Continent, 15);
	actions.sendKeys(Continent, continent);

}


    public void launchURL() {

        driver.get("https://testzenlabs.ie/general-information-form/");
    }
}