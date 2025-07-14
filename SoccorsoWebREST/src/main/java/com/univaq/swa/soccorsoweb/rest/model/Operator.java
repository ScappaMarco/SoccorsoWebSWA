package com.univaq.swa.soccorsoweb.rest.model;

import java.util.List;

public class Operator {
    private int id;
    private String name;
    private String email;
    private String username;
    private List<String> licenses;
    private List<String> skills;
    private boolean free;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getLicenses() {
        return this.licenses;
    }

    public void setLicenses(List<String> licenses) {
        this.licenses = licenses;
    }

    public void addLicense(String license) {
        this.licenses.add(license);
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public boolean isFree() {
        return this.free;
    }

    public void setFree() {
        this.free = true;
    }

    public void setNotFree() {
        this.free = false;
    }
}