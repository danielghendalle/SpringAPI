package com.algosoft.challenge.controller;

import com.algosoft.challenge.model.entity.Locality;
import com.algosoft.challenge.model.service.LocalityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "localities")
public class LocalityController {


    private final LocalityService localityService;

    public LocalityController(LocalityService localityService) {
        this.localityService = localityService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Locality> getById(@PathVariable Long id) {
        return ResponseEntity.ok(localityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Locality> post(@Valid @RequestBody Locality locality) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localityService.saveLocality(locality));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Locality> put(@PathVariable Long id, @Valid @RequestBody Locality locality) {
        return ResponseEntity.status(HttpStatus.OK).body(localityService.updateLocality(id, locality));

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        localityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
