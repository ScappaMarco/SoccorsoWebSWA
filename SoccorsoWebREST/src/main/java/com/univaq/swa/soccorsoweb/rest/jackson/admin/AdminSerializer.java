package com.univaq.swa.soccorsoweb.rest.jackson.admin;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.univaq.swa.soccorsoweb.rest.model.Admin;

import java.io.IOException;

public class AdminSerializer extends JsonSerializer<Admin> {

    @Override
    public void serialize(Admin admin, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", admin.getId());
        jsonGenerator.writeStringField("name", admin.getName());
        jsonGenerator.writeStringField("email", admin.getEmail());
        jsonGenerator.writeStringField("username", admin.getUsername());
        jsonGenerator.writeObjectField("licenses", admin.getLicenses());
        jsonGenerator.writeBooleanField("active", admin.isActive());
        jsonGenerator.writeEndObject();
    }
}
