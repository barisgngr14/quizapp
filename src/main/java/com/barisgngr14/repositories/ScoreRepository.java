package com.barisgngr14.repositories;

import com.barisgngr14.entities.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Scores, String> {
}
