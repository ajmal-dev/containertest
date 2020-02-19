package test.java.testcases;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import test.java.globalFunctions.*;
import test.java.pages.googlePageObjects;

import java.io.IOException;
import java.net.URL;

public class test
{
    public test() throws IOException, InterruptedException { }
    launch l=new launch();                            //Instance of launch class for driver instantiation.
    propertiesReader propfile=new propertiesReader(); //Instance of properties reader file
    RemoteWebDriver driver=l.launching(propfile.propertiesRead("webbrowser")); //initializing the Remotewebdriver
    extentReport reportObject=new extentReport();     // Instance of extent report
    mailing mail=new mailing();                       //mailing instance
    WebDriverWait wait=new WebDriverWait(driver,20);
    googlePageObjects gpageObjects=new googlePageObjects(driver); //google search page object factory.


    @Test                                             // Testcase for searching a subject on google
    public void Openapp() throws IOException, InterruptedException
{
    reportObject.extent.attachReporter(reportObject.htmlReporter);

    //The first test Scenario : Google search
    ExtentTest test1=reportObject.extent.createTest("GoogleSearch","Searching a subject on google and getting result");
    //The sub-testcases of the Scenario( 3 Nodes)
    ExtentTest node1=test1.createNode("Open Google","Open the ggole Search engine");
    ExtentTest node2=test1.createNode("Enter Subject","Enter the search subject in the search field");
    ExtentTest node3=test1.createNode("Search the subject","Click on the search button for searching the subject");
    Thread.sleep(3000);


    try
    {
        driver.get("http://www.google.com");
    }
    catch (Exception e)
    {
        node1.log(Status.FAIL,"The google website was not not loaded for some reasons");
    }
    By googleLogo=By.xpath("//img[@alt='Google']");
    try {
        wait.until(ExpectedConditions.titleIs("www.google.com"));
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    node1.log(Status.PASS,"The google page is loaded");
    System.out.println(driver.getTitle());
    try
    {
        gpageObjects.enterSubject("Hongkong");

    }
    catch (Exception e)
    {
        node2.log(Status.FAIL,"The search field was unable to find and enter the subject");
    }
    node2.log(Status.PASS,"The subject is successfully entered");
    By searchbutton=By.xpath("(//center//input[@value='Google Search'])[1]");
    By SearchTime=By.xpath("//div[@id='slim_appbar']");
    try
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchTime));
    }
    catch (Exception e)
    {
        node3.log(Status.FAIL,"The subject was not searched due to network error");
    }
     node3.log(Status.PASS,"The search is complete");
     test1.log(Status.PASS,"The first testcase for google search is complete.");
     driver.quit();

}

    @AfterTest                                        // Flushing the report and sending extent report via mail
    void endTest() throws EmailException, IOException
{
    reportObject.extent.flush();
    mail.sendMail();
}

}
