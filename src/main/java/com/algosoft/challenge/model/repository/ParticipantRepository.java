package com.algosoft.challenge.model.repository;

import com.algosoft.challenge.model.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {


}
