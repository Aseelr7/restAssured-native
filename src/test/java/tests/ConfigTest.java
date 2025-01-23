package tests;

import config.ConfigLoader;

import java.util.Properties;

public class ConfigTest {

    public static void main(String[] args) {
       Properties properties = ConfigLoader.getProperties();
        System.out.println(properties);
        // System.out.println( ConfigLoader.getProperties());

       // boolean switchValue = Boolean.parseBoolean( ConfigLoader.getProperty("test.execute"));
        boolean switchValue = Boolean.parseBoolean(properties.getProperty("test.execute"));
        System.out.println(switchValue);

    }
}
