package invi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesHandler {
    private static final Logger LOGGER = Logger.getLogger(PropertiesHandler.class.getName());
    private static PropertiesHandler instance = null;
    private static String rootPath;
    private static String configPropertiesPath;
    private static Properties configProperties;

    public static String getProperty(String fileName, String property) {
        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        configPropertiesPath = rootPath + fileName;
        configProperties = new Properties();

        try {
            configProperties.load(new FileInputStream(configPropertiesPath));
        } catch (FileNotFoundException e) {
            LOGGER.config("could not find config.properties file");
        } catch (IOException e) {
            LOGGER.config("could not load config.properties file");
        }

        return PropertiesHandler.configProperties.getProperty(property);
    }
}
