package com.univaq.swa.soccorsoweb.rest.utils;

import com.github.javafaker.Faker;
import com.univaq.swa.soccorsoweb.rest.model.*;
import com.univaq.swa.soccorsoweb.rest.model.enumerations.RequestStatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DummyGenerator {
    private static final Faker faker = new Faker();

    public static Operator generateDummyOperator() {
        Operator op = new Operator();
        op.setId(faker.random().nextInt(1,1000));
        op.setName("Marco Scappa");
        op.setEmail("marco.scappa@ex.com");
        op.setUsername("mscappa02");
        op.setLicenses(new ArrayList<String>());
        op.setSkills(new ArrayList<String>());
        op.setFree();

        return op;
    }

    public static Admin generateDummyAdmin() {
        Admin ad = new Admin();
        ad.setId(faker.random().nextInt(1,1000));
        ad.setName("Pippo Rossi");
        ad.setEmail("pippo.rossi@ex.com");
        ad.setUsername("pippoRossi88");
        ad.setLicenses(new ArrayList<String>());
        ad.setActive();

        return ad;
    }

    public static Mission generateDummyOngoingMission() {
        Mission mso = new Mission();
        mso.setTeamLeader(generateDummyOperator());
        mso.setTeam(new ArrayList<Operator>());
        mso.setObiettivo("Obiettivo");
        mso.setStartTime(LocalDateTime.of(2025, 6, 22, 17, 55, 44));
        mso.setEndTime(null);
        mso.setEndingAdmin(null);

        return mso;
    }

    public static Mission generateDummyEndedMission() {
        Mission mse = new Mission();
        mse.setTeamLeader(generateDummyOperator());
        mse.setTeam(new ArrayList<Operator>());
        mse.setObiettivo("Obiettivo");
        mse.setStartTime(LocalDateTime.of(2025, 6, 22, 17, 55, 44));
        mse.setEndTime(LocalDateTime.of(2025, 7, 1, 16, 43, 22));
        mse.setLivelloSuccesso(faker.random().nextInt(0,5));
        mse.setEndingAdmin(generateDummyAdmin());

        return mse;
    }

    public static User generateDummyUser() {
        User us = new User();
        us.setName("Mario Rossi");
        us.setEmail("mario.rossi@ex.com");

        return us;
    }

    public static HelpRequest generateDummyOngoingHelpRequest() {
        HelpRequest hro = new HelpRequest();
        hro.setId(1);
        hro.setDescription("Aiuto il mio gatto è finito su un albero!!!");
        hro.setPosizione(new Position());
        hro.setSegnalante(generateDummyUser());
        hro.setStatoRichiesta(RequestStatusEnum.IN_CORSO);
        hro.setMissione(generateDummyOngoingMission());

        return hro;
    }

    public static HelpRequest generateDummyClosedHelpRequest() {
        HelpRequest hre = new HelpRequest();
        hre.setId(2);
        hre.setDescription("Aiuto il mio gatto è finito su un albero!!!");
        hre.setPosizione(new Position());
        hre.setSegnalante(generateDummyUser());
        hre.setStatoRichiesta(RequestStatusEnum.CHIUSA);
        hre.setMissione(generateDummyEndedMission());

        return hre;
    }
}