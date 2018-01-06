package utility.propertiesManager;

import utility.globalConst.FilePath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utility.globalConst.FilePath.resourcesPath;

/**
 * Created by Saurav PC on 06-01-2018.
 */
public class AutProperties {

    private static final String PROPERTIES_FILE = resourcesPath + "\\automation.properties";
    private static Properties properties = new Properties();

    /**
     * static method to get Instance of class AutProperties
     * @return
     */
    public static AutProperties getInstance() {
        final AutProperties me = new AutProperties();
        me.loadProperties(me.getPropertiesFile());
        return me;
    }

    /**
     * Step 1
     *
     * Get the Property file as input stream
     * @return
     */
    private InputStream getPropertiesFile() {
        InputStream fis = null;
        try {
            fis = new FileInputStream(PROPERTIES_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fis;
    }

    /**
     * Step 2
     *
     * Load Properties
     * @param propertiesStream
     */
    private void loadProperties(InputStream propertiesStream) {
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }


}
