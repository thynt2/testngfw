package utilities;

import common.Constants;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;
    private final String propertyFilePath = Constants.getConstants().getProjectPath() + "/src/main/resources/config/config.properties";

    private static ConfigReader configReader;

    public ConfigReader() {
        properties = ConfigReader.propertyLoader(propertyFilePath);
    }

    private static Properties propertyLoader(String propertyFilePath) {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file" + propertyFilePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties not found at " + propertyFilePath);
        }
        return properties;
    }

    public static synchronized ConfigReader getConfigReader() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    public String getUserUrl() {
        String url = properties.getProperty("userUrl");
        if (url != null) {
            return url;
        } else throw new RuntimeException("User Url not found");
    }

    public String getAdminUrl() {
        String url = properties.getProperty("adminUrl");
        if (url != null) {
            return url;
        } else throw new RuntimeException("Admin Url not found");
    }
}
