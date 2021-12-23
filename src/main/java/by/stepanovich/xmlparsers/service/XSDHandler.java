package by.stepanovich.xmlparsers.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class XSDHandler {
    private Properties PROPERTIES;
    private static final Logger LOGGER = LogManager.getLogger(XSDHandler.class);
    private String propertiesFile = "out/artifacts/parser_war_exploded/WEB-INF/classes/application.properties";
    private String propertiesName = "tariffs.xsd";
    private File XSDFile;

    public File getXSDFile() {
        return XSDFile;
    }

    {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(propertiesFile));
            XSDFile = new File(PROPERTIES.getProperty(propertiesName));
        } catch (IOException e) {
            LOGGER.error("Properties not found", e);
        }
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getPropertiesFile() {
        return propertiesFile;
    }

    public void setPropertiesFile(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

}
