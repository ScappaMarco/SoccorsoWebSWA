package com.univaq.swa.soccorsoweb.rest.business.service;

import com.univaq.swa.soccorsoweb.rest.model.HelpRequest;
import com.univaq.swa.soccorsoweb.rest.model.Operator;
import javassist.NotFoundException;

import java.util.List;

public interface OperatorService {
    Operator getOperator(int operatorId) throws NotFoundException;
    int addOperator(Operator op);
    void deleteOperator(int operatorId) throws NotFoundException;
    List<HelpRequest> getOperatorOngoingMissions(int operatorId) throws NotFoundException;
    List<HelpRequest> getOperatorClosedMission(int operatorId) throws NotFoundException;
    List<Operator> getOperatorPerStatus(String status);
    List<Operator> getFreeOperators();
    List<Operator> getBusyOperators();
}