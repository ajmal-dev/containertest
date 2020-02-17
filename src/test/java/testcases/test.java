package test.java.testcases;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.java.globalFunctions.extentReport;
import test.java.globalFunctions.mailing;
import test.java.globalFunctions.propertiesReader;
import test.java.globalFunctions.runChromeImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class test
{
@BeforeTest
        public void InitialSet() throws IOException, InterruptedException {
    runChromeImage run=new runChromeImage();
    run.createChromeContainer();
}
    //Create an instance of the extent report Class
    extentReport reportObject=new extentReport();
    mailing mail=new mailing();
    propertiesReader propfile=new propertiesReader();

    public test() throws FileNotFoundException {
    }


    @Test
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

    //Remote Web driver
    DesiredCapabilities dc=DesiredCapabilities.chrome();
    // Provide the Ipaddress of the local machine (Note: In numerics)
    URL url=new URL("http://"+propfile.propertiesRead("Standalone_server_ipAddress")+":4444/wd/hub");
    RemoteWebDriver driver=new RemoteWebDriver(url,dc);

    //Driver
//    System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
//    WebDriver driver=new ChromeDriver();
    //Webdriver wait instance for creating explicit waits.
    WebDriverWait wait=new WebDriverWait(driver,20);
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
        wait.until(ExpectedConditions.titleIs("Google"));
    }catch (Exception e)
    {
        e.printStackTrace();
    }
    node1.log(Status.PASS,"The google page is loaded");
    System.out.println(driver.getTitle());
    By googleSearchField=By.xpath("//input[@title='Search']");
    try
    {
        driver.findElement(googleSearchField).sendKeys("HongKong", Keys.ENTER);
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
@AfterTest
    void endTest() throws EmailException, IOException {
    reportObject.extent.flush();
    mail.sendMail();
    }





}
