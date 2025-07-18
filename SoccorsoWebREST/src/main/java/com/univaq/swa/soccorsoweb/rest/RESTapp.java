package com.univaq.swa.soccorsoweb.rest;

import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;
import com.univaq.swa.soccorsoweb.rest.jackson.ObjectMapperContextResolver;
import com.univaq.swa.soccorsoweb.rest.resources.HelpRequestsResource;
import com.univaq.swa.soccorsoweb.rest.resources.OperatorsResource;
import com.univaq.swa.soccorsoweb.rest.security.AuthLoggedFilter;
import com.univaq.swa.soccorsoweb.rest.security.AuthenticationResource;
import com.univaq.swa.soccorsoweb.rest.security.CORSFilter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class RESTapp extends Application {
    private final Set<Class<?>> classes;

    public RESTapp() {
        HashSet<Class<?>> c = new HashSet<>();

        //resoyrces
        c.add(AuthenticationResource.class);
        c.add(HelpRequestsResource.class);
        c.add(OperatorsResource.class);

        //utils
        c.add(JacksonJsonProvider.class);
        c.add(ObjectMapperContextResolver.class);

        //filters
        c.add(CORSFilter.class);
        c.add(AuthLoggedFilter.class);

        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return this.classes;
    }
}