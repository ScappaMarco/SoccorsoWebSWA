package com.univaq.swa.soccorsoweb.rest.jackson.operator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.univaq.swa.soccorsoweb.rest.model.Operator;

import java.io.IOException;
import java.util.ArrayList;

public class OperatorDeserializer extends JsonDeserializer<Operator> {

    @Override
    public Operator deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Operator op = new Operator();

        if(node.has("id")) {
            op.setId(node.get("id").asInt());
        }
        if(node.has("name")) {
            op.setName(node.get("name").asText());
        }
        if(node.has("email")) {
            op.setEmail(node.get("email").asText());
        }
        if(node.has("username")) {
            op.setUsername(node.get("username").asText());
        }
        if(node.has("licenses")) {
            op.setLicenses(jsonParser.getCodec().treeToValue(node.get("licenses"), ArrayList.class));
        }
        if(node.has("skills")) {
            op.setSkills(jsonParser.getCodec().treeToValue(node.get("skills"), ArrayList.class));
        }
        if(node.has("free")) {
            op.setStatus(node.get("free").asBoolean());
        }

        return op;
    }
}