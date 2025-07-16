package com.univaq.swa.soccorsoweb.rest.jackson.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.univaq.swa.soccorsoweb.rest.model.User;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        User user = new User();

        if(node.has("nome")) {
            user.setName(node.get("nome").asText());
        }

        if(node.has("email")) {
            user.setEmail(node.get("email").asText());
        }

        return user;
    }
}