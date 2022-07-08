package com.algosoft.challenge.model.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Locality extends GeneralEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    private String latitude;

    private String longitude;

    public Locality() {
    }

    public Locality(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
