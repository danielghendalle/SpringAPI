package com.algosoft.challenge.controller;


import com.algosoft.challenge.model.entity.Locality;
import com.algosoft.challenge.model.service.LocalityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LocalityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private LocalityService localityService;

    private static final String PATH = "/localities";

    @Test
    public void findLocality() throws Exception {

        Locality localityFind = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(localityFind);

        mockMvc.perform(get(PATH + "/" + localityFind.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(localityFind)))
                .andExpect(status().isOk());

    }

    @Test
    public void createLocality() throws Exception {

        this.mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(new Locality("name", "123456789", "123456789"))))
                .andExpect(status().isCreated());


    }

    @Test
    public void updateLocality() throws Exception {


        Locality localityUpdate = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(localityUpdate);


        mockMvc.perform(put(PATH + "/" + localityUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(localityUpdate)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteLocality() throws Exception {

        Locality localityDelete = new Locality("name", "123456789", "123456789");
        localityService.saveLocality(localityDelete);

        mockMvc.perform(delete(PATH + "/" + localityDelete.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(localityDelete)))
                .andExpect(status().isNoContent());

    }


}
