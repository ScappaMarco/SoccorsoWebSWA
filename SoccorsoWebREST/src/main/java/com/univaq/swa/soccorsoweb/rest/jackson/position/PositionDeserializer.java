package com.univaq.swa.soccorsoweb.rest.jackson.position;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.univaq.swa.soccorsoweb.rest.model.Position;

import java.io.IOException;

public class PositionDeserializer extends JsonDeserializer<Position> {

    @Override
    public Position deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Position position = new Position();

        if(node.has("address")) {
            position.setIndirizzo(node.get("address").asText());
        }
        if(node.has("coordinates")) {
            position.setCoordinate(node.get("coordinates").asText());
        }

        return position;
    }
}