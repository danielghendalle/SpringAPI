package com.algosoft.challenge.model.entity;

import com.algosoft.challenge.model.entity.enums.Situations;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Historic extends GeneralEntity {

    @ManyToOne(optional = false)
    private Commitment commitment;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @NotNull
    @Column(nullable = false)
    private Situations situations;


    public Historic() {
    }

    public Historic(Commitment commitment, LocalDateTime dateTime, Situations situations) {
        this.commitment = commitment;
        this.dateTime = dateTime;
        this.situations = situations;
    }

    public Commitment getCommitment() {
        return commitment;
    }

    public void setCommitment(Commitment commitment) {
        this.commitment = commitment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Situations getSituations() {
        return situations;
    }

    public void setSituations(Situations situations) {
        this.situations = situations;
    }
}
