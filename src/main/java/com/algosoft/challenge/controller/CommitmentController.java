package com.algosoft.challenge.controller;


import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.service.CommitmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "commitments")
public class CommitmentController {

    private final CommitmentService commitmentService;

    public CommitmentController(CommitmentService commitmentService) {
        this.commitmentService = commitmentService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Commitment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(commitmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Commitment> post(@Valid @RequestBody Commitment commitment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commitmentService.saveCommitment(commitment));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Commitment> put(@PathVariable Long id, @Valid @RequestBody Commitment commitment) {
        return ResponseEntity.status(HttpStatus.OK).body(commitmentService.updateCommitment(id, commitment));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commitmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
