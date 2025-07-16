package com.univaq.swa.soccorsoweb.rest.jackson.helpRequest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;

import java.io.IOException;

public class HelpRequestSerializer extends JsonSerializer<HelpRequest> {

    @Override
    public void serialize(HelpRequest hr, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", hr.getId());
        jsonGenerator.writeStringField("description", hr.getDescription());
        jsonGenerator.writeObjectField("position",  hr.getPosizione());
        jsonGenerator.writeObjectField("reporter", hr.getSegnalante());
        jsonGenerator.writeObjectField("requestStatus", hr.getStatoRichiesta());
        jsonGenerator.writeObjectField("mission", hr.getMissione());
        jsonGenerator.writeEndObject();
    }
}