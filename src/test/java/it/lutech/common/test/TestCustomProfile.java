package it.lutech.common.test;

import io.quarkus.test.junit.QuarkusTestProfile;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *   Read application-test.properties
 */
public abstract class TestCustomProfile implements QuarkusTestProfile {
    protected String configProfile;
    protected String type;

    protected TestCustomProfile(String configProfile) {
        this.configProfile=configProfile;
        this.type="properties";
    }
    protected TestCustomProfile(String configProfile, String type) {
        this.configProfile=configProfile;
        this.type=type;
    }

    @Override
    public String getConfigProfile() {
        return this.configProfile;
    }

    @Override
    public Map<String, String> getConfigOverrides() {
        if ("properties".equals(type)) {
            return loadProperties("application-" + getConfigProfile() + "."+type);
        } else if ("yaml".equals(type)) {
            return loadYaml("application-" + getConfigProfile() + "."+type);
        } else if ("yml".equals(type)) {
            return loadYaml("application-" + getConfigProfile() + "."+type);
        } else {
            throw new IllegalArgumentException("Invalid type for profiled application config file.");
        }
    }

    private Map<String, String> loadYaml(String fileName) {
        Yaml yaml = new Yaml();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("File YAML not found: " + fileName);
            }
            Map<String, String> flatMap = new HashMap<>();
            flattenMap("", yaml.load(input), flatMap);
            return flatMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private static void flattenMap(String prefix, Map<String, Object> source, Map<String, String> target) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();

            if (entry.getValue() instanceof Map) {
                flattenMap(key, (Map<String, Object>) entry.getValue(), target);
            } else if (entry.getValue() instanceof Collection) {
                int i=0;
                for (Map<String, Object> element : (Collection<Map<String, Object>>) entry.getValue()) {
                    flattenMap(key+"["+i+"]", element, target);
                }
            } else {
                target.put(key, entry.getValue().toString());
            }
        }
    }
}
