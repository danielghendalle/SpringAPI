package com.algosoft.challenge.model.entity;

import com.algosoft.challenge.model.entity.enums.Situations;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Commitment extends GeneralEntity {

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @NotEmpty
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Participant> participants;

    @NotNull
    @ManyToOne(optional = false)
    private Locality locality;

    @NotNull
    @Column(nullable = false)
    private Situations situations;

    public Commitment() {
    }

    public Commitment(LocalDateTime dateTime, String description, List<Participant> participants, Locality locality, Situations situations) {
        this.dateTime = dateTime;
        this.description = description;
        this.participants = participants;
        this.locality = locality;
        this.situations = situations;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public Situations getSituations() {
        return situations;
    }

    public void setSituations(Situations situations) {
        this.situations = situations;
    }
}

