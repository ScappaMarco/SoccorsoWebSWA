package com.univaq.swa.soccorsoweb.rest.jackson.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.univaq.swa.soccorsoweb.rest.model.User;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("nome", user.getNome());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeEndObject();
    }
}