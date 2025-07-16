package com.univaq.swa.soccorsoweb.rest.business;

public class HelpRequestServiceFactory {
    private static final HelpRequestServiceImpl helprequest = new HelpRequestServiceImpl();

    public static HelpRequestServiceImpl getHelpRequestService() {
        return helprequest;
    }
}