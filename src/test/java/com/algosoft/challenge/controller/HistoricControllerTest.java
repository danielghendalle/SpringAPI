package com.algosoft.challenge.controller;


import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Historic;
import com.algosoft.challenge.model.entity.Locality;
import com.algosoft.challenge.model.entity.Participant;
import com.algosoft.challenge.model.entity.enums.Situations;
import com.algosoft.challenge.model.service.CommitmentService;
import com.algosoft.challenge.model.service.HistoricService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HistoricControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private HistoricService historicService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private LocalityService localityService;

    @Autowired
    private CommitmentService commitmentService;

    private static final String PATH = "/historics";

    @Test
    public void findHistoricByCommitmentId() throws Exception {


        Participant participant = new Participant("name", "123456789");
        participantService.saveParticipant(participant);

        Locality locality = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(locality);

        Commitment commitment = new Commitment(LocalDateTime.now(), "Teste", List.of(participant), locality, Situations.PENDENTE);
        commitmentService.saveCommitment(commitment);

        Historic historic = new Historic(commitment, LocalDateTime.now(), Situations.PENDENTE);

        mockMvc.perform(get(PATH + "/commitments" + "/" + commitment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(historic)))
                .andExpect(status().isOk());

    }

}
