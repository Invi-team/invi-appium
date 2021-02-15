package invi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesHandler {
    private static final Logger LOGGER = Logger.getLogger(PropertiesHandler.class.getName());
    private static PropertiesHandler instance = null;
    private String rootPath;
    private String configPropertiesPath;
    private Properties configProperties;


    private PropertiesHandler() {
        rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        configPropertiesPath = rootPath + "config.properties";
        configProperties = new Properties();

        try {
            configProperties.load(new FileInputStream(this.configPropertiesPath));
        } catch (FileNotFoundException e) {
            LOGGER.config("could not find config.properties file");
        } catch (IOException e) {
            LOGGER.config("could not load config.properties file");
        }
    }

    public static PropertiesHandler getInstance() {
        if (instance == null) {
            instance = new PropertiesHandler();
        }
        return instance;
    }

    public static String getProperty(String property) {
        return PropertiesHandler.getInstance().configProperties.getProperty(property);
    }
}
