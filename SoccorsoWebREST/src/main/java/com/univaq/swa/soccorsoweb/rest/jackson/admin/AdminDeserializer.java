package com.univaq.swa.soccorsoweb.rest.jackson.admin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.univaq.swa.soccorsoweb.rest.model.Admin;

import java.io.IOException;
import java.util.ArrayList;

public class AdminDeserializer extends JsonDeserializer<Admin> {

    @Override
    public Admin deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Admin admin = new Admin();

        if(node.has("id")) {
            admin.setId(node.get("id").asInt());
        }
        if(node.has("name")) {
            admin.setName(node.get("name").asText());
        }
        if(node.has("email")) {
            admin.setEmail(node.get("email").asText());
        }
        if(node.has("username")) {
            admin.setUsername(node.get("username").asText());
        }
        if(node.has("licenses")) {
            admin.setLicenses(jsonParser.getCodec().treeToValue(node.get("licenses"), ArrayList.class));
        }
        if(node.has("active")) {
            admin.setStatus(node.get("active").asBoolean());
        }

        return admin;
    }
}
