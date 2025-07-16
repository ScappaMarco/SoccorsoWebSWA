package com.univaq.swa.soccorsoweb.rest.business;

import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;
import com.univaq.swa.soccorsoweb.rest.model.Mission;
import com.univaq.swa.soccorsoweb.rest.model.Position;
import com.univaq.swa.soccorsoweb.rest.model.User;
import javassist.NotFoundException;

import java.util.List;

public interface HelpRequestService {
    HelpRequest getHelpRequest(int helprequestId) throws NotFoundException;
    int addRequest(HelpRequest hr);
    void deleteRequest(int helprequestId) throws NotFoundException;
    Mission getMissione(int helprequestId) throws NotFoundException;
    void setMission(int helprequestId, Mission mission) throws NotFoundException;
    User getSegnalante(int helprequestId) throws NotFoundException;
    void setSegnalante(int helprequestId, User segnalante) throws NotFoundException;
    Position getPosition(int helprequestId) throws NotFoundException;
    void setPosition(int helprequestId, Position position) throws NotFoundException;
    List<HelpRequest> getOngoingHelpRequests();
    List<HelpRequest> getClosedHelpRequests();
    List<HelpRequest> getIgnoredHelpRequests();
    List<HelpRequest> getPendingHelpRequests();
}
