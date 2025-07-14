package com.univaq.swa.soccorsoweb.rest.model;

import com.univaq.swa.soccorsoweb.rest.model.enumerations.RequestStatusEnum;

public class HelpRequest {
    private int id;
    private String description;
    private Position posizione;
    private User segnalante;
    private RequestStatusEnum statoRichiesta;
    private Mission missione;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Position getPosizione() {
        return this.posizione;
    }

    public void setPosizione(Position posizione) {
        this.posizione = posizione;
    }

    public User getSegnalante() {
        return this.segnalante;
    }

    public void setSegnalante(User segnalante) {
        this.segnalante = segnalante;
    }

    public RequestStatusEnum getStatoRichiesta() {
        return this.statoRichiesta;
    }

    public void setStatoRichiesta(RequestStatusEnum statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    }

    public Mission getMissione() {
        return this.missione;
    }

    public void setMissione(Mission missione) {
        this.missione = missione;
    }
}