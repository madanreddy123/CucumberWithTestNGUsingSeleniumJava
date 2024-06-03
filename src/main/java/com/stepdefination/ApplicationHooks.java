package com.stepdefination;

import com.base.ConfigReader;
import com.base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;
import java.util.Properties;

public class ApplicationHooks {

    WebDriver driver;
    Properties prop;
    ConfigReader configReader;
    DriverManager driverManager;


    public ApplicationHooks(DriverManager driverManager){
        try {
            this.driverManager=driverManager;
            driver=driverManager.getDriverInstance();
            configReader = new ConfigReader();
            prop = configReader.init_prop();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @After()
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            byte[] img=(byte[]) ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(img,"image/jpeg",scenario.getName());
        }
        driverManager.closeBrowser();
    }

}
