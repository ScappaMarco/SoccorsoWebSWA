package com.univaq.swa.soccorsoweb.rest.jackson.mission;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.univaq.swa.soccorsoweb.rest.model.Admin;
import com.univaq.swa.soccorsoweb.rest.model.Mission;
import com.univaq.swa.soccorsoweb.rest.model.Operator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionDeserializer extends JsonDeserializer<Mission> {

    @Override
    public Mission deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Mission mission = new Mission();

        if(node.has("teamLeader")) {
            mission.setTeamLeader(jsonParser.getCodec().treeToValue(node.get("teamLeder"), Operator.class));
        }
        if(node.has("team")) {
            mission.setTeam(jsonParser.getCodec().treeToValue(node.get("team"), ArrayList.class));
        }
        if(node.has("obiettivo")) {
            mission.setObiettivo(node.get("obiettivo").asText());
        }
        if(node.has("startTime")) {
            mission.setStartTime(jsonParser.getCodec().treeToValue(node.get("startTime"), LocalDateTime.class));
        }
        if(node.has("endTime")) {
            mission.setStartTime(jsonParser.getCodec().treeToValue(node.get("endTime"), LocalDateTime.class));
        }
        if(node.has("livelloSuccesso")) {
            mission.setLivelloSuccesso(node.get("livelloSuccesso").asInt());
        }
        if(node.has("endingAdmin")) {
            mission.setEndingAdmin(jsonParser.getCodec().treeToValue(node.get("endingAdmin"), Admin.class));
        }

        return mission;
    }
}