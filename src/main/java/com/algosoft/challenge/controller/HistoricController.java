package com.algosoft.challenge.controller;

import com.algosoft.challenge.model.entity.Historic;
import com.algosoft.challenge.model.service.HistoricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "historics")
public class HistoricController {

    private final HistoricService historicService;

    public HistoricController(HistoricService historicService) {
        this.historicService = historicService;
    }

    @GetMapping(value = "commitments/{id}")
    public ResponseEntity<List<Historic>> findByCommitmentId(@PathVariable Long id){

      return ResponseEntity.status(HttpStatus.OK).body(historicService.findHistoricByCommitment(id));
    }

}