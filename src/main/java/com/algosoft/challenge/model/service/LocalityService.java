package com.algosoft.challenge.model.service;


import com.algosoft.challenge.exceptions.ErrorException;
import com.algosoft.challenge.exceptions.NotFoundException;
import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Locality;
import com.algosoft.challenge.model.repository.LocalityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LocalityService {

    private final LocalityRepository localityRepository;

    private final CommitmentService commitmentService;

    public LocalityService(LocalityRepository localityRepository, CommitmentService commitmentService) {
        this.localityRepository = localityRepository;
        this.commitmentService = commitmentService;
    }

    public Locality findById(Long id) {

            Optional<Locality> locality = localityRepository.findById(id);

        if (locality.isPresent()) {
            return locality.get();
        }
            throw new NotFoundException("Não foi possível encontrar a Localização");
        }

        public Locality saveLocality(Locality locality) {

        return localityRepository.save(locality);
    }

    public Locality updateLocality(Long id, @RequestBody Locality locality) {

        Optional<Locality> localitySaved = localityRepository.findById(id);

        if (localitySaved.isPresent()) {
            Locality localityUpdate = localitySaved.get();
            localityUpdate.setName(locality.getName());
            localityUpdate.setLatitude(locality.getLatitude());
            localityUpdate.setLongitude(locality.getLongitude());
            return localityRepository.save(localityUpdate);
        }
            throw new NotFoundException("Não foi possível encontrar a Localização");
        }

    public void delete(Long id) {

        Optional<Locality> locality = localityRepository.findById(id);
        if (locality.isPresent()) {
            Locality localityDelete = locality.get();

            List<Commitment> commitment = commitmentService.findByLocality(localityDelete);

            if (!commitment.isEmpty()) {
                throw new ErrorException("A Localização " + localityDelete.getName() + " não pode ser deletada porque está vinculada a um Comprmisso");
            }
        }
        try {
            localityRepository.deleteById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Não foi possível encontrar a Localização");
        }
    }
}
