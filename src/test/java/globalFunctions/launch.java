package test.java.globalFunctions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class launch


{
    public  RemoteWebDriver launching(String browser) throws IOException, InterruptedException {
        Thread.sleep(5000);

        RemoteWebDriver driver=null;
        if(browser.equalsIgnoreCase("chrome"))
        {
            propertiesReader propfile = new propertiesReader();
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            URL url = new URL("http://" + propfile.propertiesRead("ipaddress") + "/wd/hub");
            try
            {
                driver = new RemoteWebDriver(url, dc);

            }
            catch (Exception e)
            {
                System.out.println("Check the standalone server is running on the machine");
                e.printStackTrace();
            }
        }
        else if (browser.equalsIgnoreCase("firefox"))
        {
            propertiesReader propfile = new propertiesReader();
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            URL url = new URL("http://" + propfile.propertiesRead("ipaddress") + "/wd/hub");
            try {
                driver = new RemoteWebDriver(url, dc);

            }
            catch (Exception e)
            {
                System.out.println("Check the standalone server is running on the machine");
                e.printStackTrace();
            }
        }

        return driver;

    }

    public launch() throws IOException {
    }
}
