package test.java.globalFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesReader {
    public String propertiesRead(String key) throws IOException {

        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("/usr/properties.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop.load(input);
        String value=prop.getProperty(key);
        return value;
    }
}
