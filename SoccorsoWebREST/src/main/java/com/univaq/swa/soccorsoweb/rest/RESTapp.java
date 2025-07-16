package com.univaq.swa.soccorsoweb.rest;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RESTapp extends Application {
    private final Set<Class<?>> classes;

    public RESTapp() {
        HashSet<Class<?>> c = new HashSet<>();

        //resoyrces, filters, ecc

        classes = Collections.unmodifiableSet(c);
        }

    @Override
    public Set<Class<?>> getClasses() {
        return this.classes;
    }
}