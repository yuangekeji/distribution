package com.distribution.common.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    public static Map<String, Object> decode(String jsonStr,
                                             Class<Map> class1) throws JsonParseException,
            JsonMappingException, IOException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(jsonStr, class1);
    }

    public static String toJson(Object object) {
        ObjectMapper om = new ObjectMapper();
        String result = null;
        try {
            result = om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
