package com.univaq.swa.soccorsoweb.rest.jackson.helpRequest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;
import com.univaq.swa.soccorsoweb.rest.model.Mission;
import com.univaq.swa.soccorsoweb.rest.model.Position;
import com.univaq.swa.soccorsoweb.rest.model.User;
import com.univaq.swa.soccorsoweb.rest.model.enumerations.RequestStatusEnum;

import java.io.IOException;

public class HelpRequestDeserializer extends JsonDeserializer<HelpRequest> {
    @Override
    public HelpRequest deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        HelpRequest hr = new HelpRequest();

        if(node.has("id")) {
            hr.setId(node.get("id").asInt());
        }
        if(node.has("description")) {
            hr.setDescription(node.get("description").asText());
        }
        if(node.has("position")) {
            hr.setPosizione(jsonParser.getCodec().treeToValue(node.get("position"), Position.class));
        }
        if(node.has("reporter")) {
            hr.setSegnalante(jsonParser.getCodec().treeToValue(node.get("reporter"), User.class));
        }
        if(node.has("requestStatus")) {
            hr.setStatoRichiesta(jsonParser.getCodec().treeToValue(node.get("requestStatus"), RequestStatusEnum.class));
        }
        if(node.has("mission")) {
            hr.setMissione(jsonParser.getCodec().treeToValue(node.get("mission"), Mission.class));
        }

        return hr;
    }
}