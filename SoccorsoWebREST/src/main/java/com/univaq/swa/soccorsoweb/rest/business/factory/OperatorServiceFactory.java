package com.univaq.swa.soccorsoweb.rest.business.factory;

import com.univaq.swa.soccorsoweb.rest.business.impl.OperatorServiceImpl;

public class OperatorServiceFactory {
    private static final OperatorServiceImpl operatorService = new OperatorServiceImpl();

    public static OperatorServiceImpl getOperatorService() { return operatorService; }
}