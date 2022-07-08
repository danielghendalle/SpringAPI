package com.algosoft.challenge.model.entity.enums;

public enum Situations {

    PENDENTE("pendente"),
    EXECUTADO("executado"),
    CANCELADO("cancelado");

    private String situation;

    Situations(String situation) {
        this.situation = situation;
    }

    public  String getSituation(){
        return situation;
    }


}

