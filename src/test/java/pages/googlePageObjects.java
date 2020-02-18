package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.security.Key;

public class googlePageObjects

{
    RemoteWebDriver driver=null;

 public googlePageObjects(RemoteWebDriver driver)
 {
     this.driver=driver;
 }
    By googleLogo=By.xpath("//img[@alt='Google']");
    By googleSearchField=By.xpath("//input[@title='Search']");
    By searchbutton=By.xpath("(//center//input[@value='Google Search'])[1]");
    By SearchTime=By.xpath("//div[@id='slim_appbar']");

    public void enterSubject(String keywords )
    {
        driver.findElement(googleSearchField).sendKeys(keywords, Keys.ENTER);

    }


}
