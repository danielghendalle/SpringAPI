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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ParticipantControllerTest {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private LocalityService localityService;

    @Autowired
    private CommitmentService commitmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static final String PATH = "/participants";

    @Test
    public void findParticipant() throws Exception {

        Participant participantFind = new Participant("name", "123456789");
        participantService.saveParticipant(participantFind);


        mockMvc.perform(get(PATH + "/" + participantFind.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(participantFind)))
                .andExpect(status().isOk());

    }
    @Test
    public void findParticipantCommitments() throws Exception {

        Participant participant = new Participant("name", "123456789");
        participantService.saveParticipant(participant);

        Locality locality = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(locality);

        Commitment commitment = new Commitment(LocalDateTime.now(), "Teste", List.of(participant), locality, Situations.PENDENTE);
        commitmentService.saveCommitment(commitment);

        mockMvc.perform(get(PATH + "/" + participant.getId() + "/commitments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(participant)))
                .andExpect(status().isOk());

    }
    @Test
    public void findParticipantCommitmentsBySituation() throws Exception {

        Participant participant = new Participant("name", "123456789");
        participantService.saveParticipant(participant);

        Locality locality = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(locality);

        Commitment commitment = new Commitment(LocalDateTime.now(), "Teste", List.of(participant), locality, Situations.PENDENTE);
        commitmentService.saveCommitment(commitment);

        mockMvc.perform(get(PATH + "/" + participant.getId() + "/commitments" + "/"  + commitment.getSituations().getSituation().toUpperCase())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(participant)))
                .andExpect(status().isOk());

    }


    @Test
    public void createParticipant() throws Exception {


        this.mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(new Participant("name", "123456789"))))
                .andExpect(status().isCreated());


    }

    @Test
    public void updateCommitment() throws Exception {

        Participant participantUpdate = new Participant("name", "123456789");
        participantService.saveParticipant(participantUpdate);



        mockMvc.perform(put(PATH + "/" + participantUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(participantUpdate)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCommitment() throws Exception {

        Participant participantDelete = new Participant("name", "123456789");
        participantService.saveParticipant(participantDelete);

        mockMvc.perform(delete(PATH + "/" + participantDelete.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(participantDelete)))
                .andExpect(status().isNoContent());

    }

}
