package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
    public static String getProperty(String propKey) {
        FileInputStream fis;
        String prop = "";
        try {
            Properties property = new Properties();
            fis = new FileInputStream("src/main/resources/login.properties");
            property.load(fis);
            prop = property.getProperty(propKey);

        } catch (FileNotFoundException e) {
            Logs.error("Properties file is absent", e);
        } catch (IOException e) {
            Logs.error(e);
            System.out.println(e.getMessage());
        }
        return prop;
    }
}
