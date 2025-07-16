package com.univaq.swa.soccorsoweb.rest.jackson.position;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.univaq.swa.soccorsoweb.rest.model.Position;

import java.io.IOException;

public class PositionSerializer extends JsonSerializer<Position> {

    @Override
    public void serialize(Position position, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("address", position.getIndirizzo());
        jsonGenerator.writeStringField("coordinates", position.getCoordinate());
        jsonGenerator.writeEndObject();
    }
}