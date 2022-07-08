package com.algosoft.challenge.model.repository;


import com.algosoft.challenge.model.entity.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

    List<Historic> findByCommitmentId(Long id);

    void deleteHistoricByCommitmentId(Long id);


}
