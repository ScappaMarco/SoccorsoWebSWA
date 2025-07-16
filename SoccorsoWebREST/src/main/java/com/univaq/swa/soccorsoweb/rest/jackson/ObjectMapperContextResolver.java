package com.univaq.swa.soccorsoweb.rest.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.univaq.swa.soccorsoweb.rest.jackson.admin.AdminDeserializer;
import com.univaq.swa.soccorsoweb.rest.jackson.admin.AdminSerializer;
import com.univaq.swa.soccorsoweb.rest.jackson.helpRequest.HelpRequestDeserializer;
import com.univaq.swa.soccorsoweb.rest.jackson.helpRequest.HelpRequestSerializer;
import com.univaq.swa.soccorsoweb.rest.jackson.mission.MissionDeserializer;
import com.univaq.swa.soccorsoweb.rest.jackson.mission.MissionSerializer;
import com.univaq.swa.soccorsoweb.rest.jackson.operator.OperatorDeserializer;
import com.univaq.swa.soccorsoweb.rest.jackson.operator.OperatorSerializer;
import com.univaq.swa.soccorsoweb.rest.jackson.position.PositionDeserializer;
import com.univaq.swa.soccorsoweb.rest.jackson.position.PositionSerializer;
import com.univaq.swa.soccorsoweb.rest.jackson.user.UserDeserializer;
import com.univaq.swa.soccorsoweb.rest.jackson.user.UserSerializer;
import com.univaq.swa.soccorsoweb.rest.model.*;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ObjectMapperContextResolver() {
        this.mapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule customSerializers = new SimpleModule("CustomSerializersModule");

        //user
        customSerializers.addSerializer(User.class, new UserSerializer());
        customSerializers.addDeserializer(User.class, new UserDeserializer());

        //position
        customSerializers.addSerializer(Position.class, new PositionSerializer());
        customSerializers.addDeserializer(Position.class, new PositionDeserializer());

        //operator
        customSerializers.addSerializer(Operator.class, new OperatorSerializer());
        customSerializers.addDeserializer(Operator.class, new OperatorDeserializer());

        //admin
        customSerializers.addSerializer(Admin.class, new AdminSerializer());
        customSerializers.addDeserializer(Admin.class, new AdminDeserializer());

        //mission
        customSerializers.addSerializer(Mission.class, new MissionSerializer());
        customSerializers.addDeserializer(Mission.class, new MissionDeserializer());

        //helpRequest
        customSerializers.addSerializer(HelpRequest.class, new HelpRequestSerializer());
        customSerializers.addDeserializer(HelpRequest.class, new HelpRequestDeserializer());

        mapper.registerModule(customSerializers);
        return mapper;
    }
}