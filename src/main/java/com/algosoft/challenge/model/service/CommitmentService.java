package com.algosoft.challenge.model.service;

import com.algosoft.challenge.exceptions.ErrorException;
import com.algosoft.challenge.exceptions.NotFoundException;
import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Locality;
import com.algosoft.challenge.model.entity.Participant;
import com.algosoft.challenge.model.entity.enums.Situations;
import com.algosoft.challenge.model.repository.CommitmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.algosoft.challenge.model.entity.enums.Situations.CANCELADO;
import static com.algosoft.challenge.model.entity.enums.Situations.EXECUTADO;

@Service
public class CommitmentService {

    private final CommitmentRepository commitmentRepository;

    private final HistoricService historicService;

    public CommitmentService(CommitmentRepository commitmentRepository, HistoricService historicService) {
        this.commitmentRepository = commitmentRepository;
        this.historicService = historicService;
    }


    public Commitment findById(Long id) {

        try {
            Optional<Commitment> commitmentId = commitmentRepository.findById(id);
            return commitmentId.get();
        } catch (NotFoundException e) {

            throw new NotFoundException("Não foi possível encontrar o Compromisso!");
        }
    }


    public Commitment saveCommitment(Commitment commitment) {
        List<Participant> participants = commitment.getParticipants().stream().filter(p -> exists(p, commitment)).collect(Collectors.toList());
        if (!participants.isEmpty()) {
            throw new ErrorException("O participante já está em um Compromisso ou possui um compromisso pendente");
        }

        Commitment save = this.commitmentRepository.save(commitment);
        historicService.generate(save);
        return save;

    }

    private boolean exists(Participant participant, Commitment commitment) {
        return this.commitmentRepository.findByParticipants(participant)
                .stream()
                .anyMatch(t -> Situations.PENDENTE.equals(t.getSituations()) && t.getDateTime().toLocalDate().equals(commitment.getDateTime().toLocalDate()));
    }

    public List<Commitment> findByParticipants(Participant participant) {

        return commitmentRepository.findByParticipants(participant);
    }

    public List<Commitment> findByLocality(Locality locality) {

        return commitmentRepository.findByLocality(locality);
    }

    public List<Commitment> findByParticipantsWithSituation(Participant participant, Situations situations) {

        return commitmentRepository.findByParticipantsAndSituations(participant, situations);
    }


    public Commitment updateCommitment(Long id, @Validated @RequestBody Commitment commitment) {

        Optional<Commitment> commitmentSaved = commitmentRepository.findById(id);


        if (commitment.getSituations() == EXECUTADO || commitment.getSituations() == CANCELADO) {
            throw new ErrorException("O compromisso não pode ser alterado porque possui a situação de: " + commitment.getSituations().getSituation().toLowerCase());
        }

        try {
            Commitment commitmentUpdate = commitmentSaved.get();
            commitmentUpdate.setDateTime(LocalDateTime.now());
            commitmentUpdate.setDescription(commitment.getDescription());
            commitmentUpdate.setParticipants(commitment.getParticipants());
            commitmentUpdate.setLocality(commitment.getLocality());
            commitmentUpdate.setSituations(commitment.getSituations());
            commitmentRepository.save(commitmentUpdate);
            historicService.generate(commitmentUpdate);
            return commitmentUpdate;
        } catch (NotFoundException e) {
            throw new NotFoundException("Não foi possível encontrar o Compromisso!");
        }
    }

    public void delete(Long id) {

        Commitment commitment = this.findById(id);

        if (commitment.getSituations() == EXECUTADO || commitment.getSituations() == CANCELADO) {
            throw new ErrorException("O compromisso não pode ser excluído porque possui a situação de: " + commitment.getSituations().getSituation().toLowerCase());
        }
        try {
            historicService.delete(id);
            commitmentRepository.deleteById(id);
        } catch (NotFoundException e) {
            throw new NotFoundException("Não foi possível encontrar o Compromisso!");
        }
    }
}

