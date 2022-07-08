package com.algosoft.challenge.controller;

import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Locality;
import com.algosoft.challenge.model.entity.Participant;
import com.algosoft.challenge.model.entity.enums.Situations;
import com.algosoft.challenge.model.service.CommitmentService;
import com.algosoft.challenge.model.service.LocalityService;
import com.algosoft.challenge.model.service.ParticipantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommitmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private CommitmentService commitmentService;

    @Autowired
    private LocalityService localityService;

    private static final String PATH = "/commitments";


    @Test
    public void findCommitment() throws Exception {

        Participant participant = new Participant("name", "123456789");
        participantService.saveParticipant(participant);

        Locality locality = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(locality);


        Commitment commitmentFind = new Commitment(LocalDateTime.now(), "Teste", List.of(participant), locality, Situations.PENDENTE);
        commitmentService.saveCommitment(commitmentFind);


        mockMvc.perform(get(PATH + "/" + commitmentFind.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commitmentFind)))
                .andExpect(status().isOk());

    }


    @Test
    public void createCommitment() throws Exception {

        Participant participant = new Participant("name", "123456789");
        participantService.saveParticipant(participant);

        Locality locality = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(locality);

        this.mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(new Commitment(LocalDateTime.now(), "Teste", List.of(participant), locality, Situations.PENDENTE))))
                .andExpect(status().isCreated());


    }

    @Test
    public void updateCommitment() throws Exception {

        Participant participant = new Participant("name", "123456789");
        participantService.saveParticipant(participant);

        Locality locality = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(locality);


        Commitment commitmentUpdate = new Commitment(LocalDateTime.now(), "Teste", List.of(participant), locality, Situations.PENDENTE);
        commitmentService.saveCommitment(commitmentUpdate);


        mockMvc.perform(put(PATH + "/" + commitmentUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commitmentUpdate)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCommitment() throws Exception {

        Participant participant = new Participant("name", "123456789");
        participantService.saveParticipant(participant);

        Locality locality = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(locality);


        Commitment commitmentDelete = new Commitment(LocalDateTime.now(), "Teste", List.of(participant), locality, Situations.PENDENTE);
        commitmentService.saveCommitment(commitmentDelete);


        mockMvc.perform(delete(PATH + "/" + commitmentDelete.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commitmentDelete)))
                .andExpect(status().isNoContent());

    }


}
