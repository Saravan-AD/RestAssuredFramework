package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;

    public static void initConfig() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setConfig(String key, String value) {
        properties.setProperty(key, value);
    }

    public static String getConfig(String key) {
        return properties.getProperty(key);
    }

    public static void setObject(String key, Object pojo) {
        properties.put(key, pojo);
    }

    public static Object getObject(String key) {
        return properties.get(key);
    }
}
