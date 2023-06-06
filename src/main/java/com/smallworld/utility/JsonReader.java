package com.smallworld.utility;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
    public JsonNode readJsonFile() {
        JsonNode jsonNode = null;

        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.JSON_FILE_PATH)) {
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readValue(in, JsonNode.class);            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return jsonNode;
    }

}
