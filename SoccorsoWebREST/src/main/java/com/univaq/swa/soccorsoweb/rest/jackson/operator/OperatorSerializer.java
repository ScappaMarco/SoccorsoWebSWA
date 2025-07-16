package com.univaq.swa.soccorsoweb.rest.jackson.operator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.univaq.swa.soccorsoweb.rest.model.Operator;

import java.io.IOException;

public class OperatorSerializer extends JsonSerializer<Operator> {

    @Override
    public void serialize(Operator operator, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", operator.getId());
        jsonGenerator.writeStringField("name", operator.getName());
        jsonGenerator.writeStringField("email", operator.getEmail());
        jsonGenerator.writeStringField("username", operator.getUsername());
        jsonGenerator.writeObjectField("licenses", operator.getLicenses());
        jsonGenerator.writeObjectField("skills", operator.getSkills());
        jsonGenerator.writeBooleanField("free", operator.isFree());
        jsonGenerator.writeEndObject();
    }
}