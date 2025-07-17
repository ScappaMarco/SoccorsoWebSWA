package com.univaq.swa.soccorsoweb.rest.business.factory;

import com.univaq.swa.soccorsoweb.rest.business.impl.HelpRequestServiceImpl;

public class HelpRequestServiceFactory {
    private static final HelpRequestServiceImpl helprequest = new HelpRequestServiceImpl();

    public static HelpRequestServiceImpl getHelpRequestService() {
        return helprequest;
    }
}