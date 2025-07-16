package com.univaq.swa.soccorsoweb.rest.jackson.mission;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.univaq.swa.soccorsoweb.rest.model.Mission;

import java.io.IOException;

public class MissionSerializer extends JsonSerializer<Mission> {

    @Override
    public void serialize(Mission mission, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("teamLeader", mission.getTeamLeader());
        jsonGenerator.writeObjectField("team", mission.getTeam());
        jsonGenerator.writeStringField("obiettivo", mission.getObiettivo());
        jsonGenerator.writeObjectField("startTime", mission.getStartTime());
        jsonGenerator.writeObjectField("endTime", mission.getEndTime());
        jsonGenerator.writeNumberField("livelloSuccesso", mission.getLivelloSuccesso());
        jsonGenerator.writeObjectField("endingAdmin", mission.getEndingAdmin());
        jsonGenerator.writeEndObject();
    }
}
