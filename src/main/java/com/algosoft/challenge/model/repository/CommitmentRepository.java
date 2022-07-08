package com.algosoft.challenge.model.repository;

import com.algosoft.challenge.model.entity.Commitment;
import com.algosoft.challenge.model.entity.Locality;
import com.algosoft.challenge.model.entity.Participant;
import com.algosoft.challenge.model.entity.enums.Situations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommitmentRepository extends JpaRepository<Commitment, Long> {

    List<Commitment> findByParticipants(Participant participant);

    List<Commitment> findByLocality(Locality locality);

    List<Commitment> findByParticipantsAndSituations(Participant participant, Situations situations);

}
