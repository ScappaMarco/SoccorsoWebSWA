package com.univaq.swa.soccorsoweb.rest.business.impl;

import com.univaq.swa.soccorsoweb.rest.business.service.OperatorService;
import com.univaq.swa.soccorsoweb.rest.model.Mission;
import com.univaq.swa.soccorsoweb.rest.model.Operator;
import com.univaq.swa.soccorsoweb.rest.utils.DummyGenerator;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class OperatorServiceImpl implements OperatorService {
    @Override
    public Operator getOperator(int operatorId) throws NotFoundException {
        return DummyGenerator.generateDummyOperator();
    }

    @Override
    public int addOperator(Operator op) {
        return 1;
    }

    @Override
    public void deleteOperator(int operatorId) throws NotFoundException {

    }

    @Override
    public List<Mission> getOperatorOngoingMissions(int operatorId) throws NotFoundException {
        List<Mission> missions = new ArrayList<>();
        missions.add(DummyGenerator.generateDummyOngoingMission());
        missions.add(DummyGenerator.generateDummyOngoingMission());
        return missions;
    }

    @Override
    public List<Mission> getOperatorClosedMission(int operatorId) throws NotFoundException {
        List<Mission> missions = new ArrayList<>();
        missions.add(DummyGenerator.generateDummyEndedMission());
        missions.add(DummyGenerator.generateDummyEndedMission());
        return missions;
    }

    @Override
    public List<Operator> getFreeOperators() {
        List<Operator> ops = new ArrayList<>();
        ops.add(DummyGenerator.generateDummyOperator());
        ops.add(DummyGenerator.generateDummyOperator());
        return ops;
    }

    @Override
    public List<Operator> getBusyOperators() {
        List<Operator> ops = new ArrayList<>();
        ops.add(DummyGenerator.generateDummyOperator());
        ops.add(DummyGenerator.generateDummyOperator());
        return ops;
    }
}