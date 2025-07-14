package com.univaq.swa.soccorsoweb.rest.model;

import java.time.LocalDate;
import java.util.List;

public class Mission {
    private Operator teamLeader;
    private List<Operator> team;
    private String obiettivo;
    private LocalDate startTime;
    private LocalDate endTime;
    private byte livelloSuccesso;
    private Admin endingAdmin;

    public Operator getTeamLeader() {
        return this.teamLeader;
    }

    public void setTeamLeader(Operator teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<Operator> getTeam() {
        return this.team;
    }

    public void setTeam(List<Operator> team) {
        this.team = team;
    }

    public void addOperatortoTeam(Operator op) {
        this.team.add(op);
    }

    public String getObiettivo() {
        return this.obiettivo;
    }

    public void setObiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    public LocalDate getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public byte getLivelloSuccesso() {
        return this.livelloSuccesso;
    }

    public void setLivelloSuccesso(byte livelloSuccesso) {
        this.livelloSuccesso = livelloSuccesso;
    }

    public Admin getEndingAdmin() {
        return this.endingAdmin;
    }

    public void setEndingAdmin(Admin endingAdmin) {
        this.endingAdmin = endingAdmin;
    }
}