package com.distribution.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("all")
public final class PropertyUtil {
    public static Properties getProperties(String url) throws IOException {
        Properties prop = new Properties();
        InputStream in = PropertyUtil.class.getResourceAsStream(url);
        prop.load(in);
        return prop;
    }

    public static Map<String, String> getMapProperties(String url) throws IOException {
        Map<String, String> map = new HashMap<String, String>(
                (Map) getProperties(url));
        return map;
    }

    public static String getValueProperties(String url, String param) throws IOException {
        Map map = getMapProperties(url);
        return (String) map.get(param);
    }
}