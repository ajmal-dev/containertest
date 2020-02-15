package test.java;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class test
{
@Test
    public void Openapp() throws MalformedURLException, InterruptedException {
    Thread.sleep(3000);
    DesiredCapabilities dc=DesiredCapabilities.chrome();
    URL url=new URL("http://192.168.1.109:4444/wd/hub");
    RemoteWebDriver driver=new RemoteWebDriver(url,dc);
    driver.get("http://www.google.com");
    System.out.println(driver.getTitle());
    driver.quit();
}


}
