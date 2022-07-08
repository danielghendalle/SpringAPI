package com.algosoft.challenge.model.entity;

import com.sun.istack.NotNull;


import javax.persistence.*;


@Entity
public class Participant extends GeneralEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    private String phone;

    public Participant() {

    }

    public Participant(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
