package com.algosoft.challenge.controller;


import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Participant;
import com.algosoft.challenge.model.entity.enums.Situations;
import com.algosoft.challenge.model.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Participant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.findById(id));
    }

    @GetMapping("{id}/commitments")
    public ResponseEntity<List<Commitment>> getCommitments(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.findCommitments(id));
    }

    @GetMapping("{id}/commitments/{situations}")
    public ResponseEntity<List<Commitment>> getCommitmentsWithSituations(@PathVariable Long id, @PathVariable Situations situations) {
        return ResponseEntity.ok(participantService.findCommitmentsWithSituations(id, situations));
    }

    @PostMapping
    public ResponseEntity<Participant> post(@Valid @RequestBody Participant participant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.saveParticipant(participant));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Participant> put(@PathVariable Long id, @Valid @RequestBody Participant participant) {
        return ResponseEntity.status(HttpStatus.OK).body(participantService.updateParticipant(id, participant));

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
