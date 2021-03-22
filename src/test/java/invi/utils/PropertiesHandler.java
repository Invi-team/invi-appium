package invi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesHandler {
    private static final Logger LOGGER = Logger.getLogger(PropertiesHandler.class.getName());
    private static PropertiesHandler instance = null;
    private static String rootPath;
    private static String configPropertiesPath;
    private static Properties configProperties;

    public static String getProperty(String fileName, String propertyKey) {
        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        configPropertiesPath = rootPath + fileName;
        configProperties = new Properties();

        try {
            configProperties.load(new FileInputStream(configPropertiesPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.config("could not find " + fileName + " config.properties file");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.config("could not load " + fileName + " config.properties file");
        }

        return PropertiesHandler.configProperties.getProperty(propertyKey);
    }

    public void setProperty(String fileName, String propertyKey, String propertyValue) {
        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        configPropertiesPath = rootPath + fileName;
        configProperties = new Properties();

        configProperties.setProperty(propertyKey, propertyValue);

        try {
            configProperties.store(new FileOutputStream(fileName), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.config("could not find " + fileName + " file");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.config("could not open " + fileName + " file");
        }
    }
}
