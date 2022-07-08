package com.algosoft.challenge.model.service;

import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Historic;
import com.algosoft.challenge.model.repository.HistoricRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricService {

    private final HistoricRepository historicRepository;

    public HistoricService(HistoricRepository historicRepository) {
        this.historicRepository = historicRepository;
    }


    public List<Historic> findHistoricByCommitment(Long id) {

        return historicRepository.findByCommitmentId(id);

    }

    public Historic generate(Commitment commitment) {

        Historic historic = new Historic();
        historic.setCommitment(commitment);
        historic.setDateTime(LocalDateTime.now());
        historic.setSituations(commitment.getSituations());
        return historicRepository.save(historic);


    }

    @Transactional
    public void delete(Long id) {

        this.historicRepository.deleteHistoricByCommitmentId(id);

    }

}
