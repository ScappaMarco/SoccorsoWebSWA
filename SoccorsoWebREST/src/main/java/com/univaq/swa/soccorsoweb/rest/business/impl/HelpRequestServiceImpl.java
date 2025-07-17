package com.univaq.swa.soccorsoweb.rest.business.impl;

import com.univaq.swa.soccorsoweb.rest.business.service.HelpRequestService;
import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;
import com.univaq.swa.soccorsoweb.rest.model.Mission;
import com.univaq.swa.soccorsoweb.rest.model.Position;
import com.univaq.swa.soccorsoweb.rest.model.User;
import com.univaq.swa.soccorsoweb.rest.utils.DummyGenerator;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class HelpRequestServiceImpl implements HelpRequestService {
    @Override
    public HelpRequest getHelpRequest(int helprequestId) throws NotFoundException {
        return DummyGenerator.generateDummyOngoingHelpRequest();
    }

    @Override
    public int addRequest(HelpRequest hr) {
        return 1;
    }

    @Override
    public void deleteRequest(int helprequestId) throws NotFoundException {

    }

    @Override
    public Mission getMissione(int helprequestId) throws NotFoundException {
        return DummyGenerator.generateDummyEndedMission();
    }

    @Override
    public void setMission(int helprequestId, Mission mission) throws NotFoundException {

    }

    @Override
    public User getSegnalante(int helprequestId) throws NotFoundException {
        return DummyGenerator.generateDummyUser();
    }

    @Override
    public void setSegnalante(int helprequestId, User segnalante) throws NotFoundException {

    }

    @Override
    public Position getPosition(int helprequestId) throws NotFoundException {
        return DummyGenerator.generateDummyClosedHelpRequest().getPosizione();
    }

    @Override
    public void setPosition(int helprequestId, Position position) throws NotFoundException {

    }

    @Override
    public List<HelpRequest> getOngoingHelpRequests() {
        List<HelpRequest> helprequests = new ArrayList<>();
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        return helprequests;
    }

    @Override
    public List<HelpRequest> getClosedHelpRequests() {
        List<HelpRequest> helprequests = new ArrayList<>();
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        return helprequests;
    }

    @Override
    public List<HelpRequest> getIgnoredHelpRequests() {
        List<HelpRequest> helprequests = new ArrayList<>();
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        return helprequests;
    }

    @Override
    public List<HelpRequest> getPendingHelpRequests() {
        List<HelpRequest> helprequests = new ArrayList<>();
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        helprequests.add(DummyGenerator.generateDummyClosedHelpRequest());
        return helprequests;
    }
}