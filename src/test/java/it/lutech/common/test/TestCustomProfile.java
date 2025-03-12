package it.lutech.common.test;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *   Read application-test.properties
 */
public abstract class TestCustomProfile implements QuarkusTestProfile {
    protected String configProfile;

    protected TestCustomProfile(String configProfile) {
        this.configProfile=configProfile;
    }

    @Override
    public String getConfigProfile() {
        return this.configProfile;
    }

    @Override
    public Map<String, String> getConfigOverrides() {
        return loadProperties("application-"+getConfigProfile()+".properties");
    }

    public static Map<String, String> loadProperties(String fileName) {
        Map<String, String> configMap = new HashMap<>();
        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("File not found: " + fileName);
            }
            properties.load(input);

            for (String key : properties.stringPropertyNames()) {
                configMap.put(key, properties.getProperty(key));
            }

        } catch (IOException e) {
            throw new RuntimeException("Configuration file error", e);
        }

        return configMap;
    }
}
