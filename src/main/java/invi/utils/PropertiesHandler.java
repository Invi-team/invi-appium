package invi.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesHandler {
    private static final Logger LOGGER = Logger.getLogger(PropertiesHandler.class.getName());
    private Properties configProperties;

    public String getProperty(String fileName, String propertyKey) {
        configProperties = new Properties();

        try {
            configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.config("could not find " + fileName + " config.properties file");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.config("could not load " + fileName + " config.properties file");
        }
        return configProperties.getProperty(propertyKey);
    }

    public void setProperty(String fileName, String propertyKey, String propertyValue) {
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
