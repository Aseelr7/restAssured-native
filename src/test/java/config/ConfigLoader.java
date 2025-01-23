package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

   private static Properties properties = new Properties();

    static {
        try(InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(
                "config/config.properties"
        )){
            if (input == null){
                throw new RuntimeException("Config file is either empty or doesn't exist");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the config file" + e);
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key){
        return Integer.parseInt( properties.getProperty(key));
    }

    public static boolean getBooleanProperty(String key){
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static Properties getProperties(){
        return properties;
    }


}
