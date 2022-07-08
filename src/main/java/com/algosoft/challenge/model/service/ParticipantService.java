package com.algosoft.challenge.model.service;

import com.algosoft.challenge.exceptions.ErrorException;
import com.algosoft.challenge.exceptions.NotFoundException;
import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Participant;
import com.algosoft.challenge.model.entity.enums.Situations;
import com.algosoft.challenge.model.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    private final CommitmentService commitmentService;

    public ParticipantService(ParticipantRepository participantRepository, CommitmentService commitmentService) {
        this.participantRepository = participantRepository;
        this.commitmentService = commitmentService;
    }


    public Participant findById(Long id) {

        Optional<Participant> participantId = participantRepository.findById(id);

        if (participantId.isPresent()) {
            return participantId.get();
        }
        throw new NotFoundException("Não foi possível encontrar o Participante!");

    }

    public List<Commitment> findCommitments(Long id) {
        Participant participant = this.findById(id);
        return commitmentService.findByParticipants(participant);
    }

    public List<Commitment> findCommitmentsWithSituations(Long id, Situations situations) {
        Participant participant = this.findById(id);
        return commitmentService.findByParticipantsWithSituation(participant, situations);
    }

    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Long id, Participant participant) {

        Optional<Participant> participantSaved = participantRepository.findById(id);

        if (participantSaved.isPresent()) {
            Participant participantUpdate = participantSaved.get();
            participantUpdate.setName(participant.getName());
            participantUpdate.setPhone(participant.getPhone());

            return participantRepository.save(participantUpdate);
        }
        throw new NotFoundException("Não foi possível encontrar o participante");
    }

    public void delete(Long id) {

        Optional<Participant> participant = participantRepository.findById(id);
        Participant participantDelete = participant.get();

        List<Commitment> commitment = commitmentService.findByParticipants(participantDelete);

        if (!commitment.isEmpty()) {
            throw new ErrorException("O Participante " + participantDelete.getName() + " não pode ser deletado porque está vinculado a um Comprmisso");
        }
        if (participant.isPresent()) {
            participantRepository.deleteById(id);
            return;

        }
        throw new NotFoundException("Não foi possível encontrar o Participante");

    }
}

