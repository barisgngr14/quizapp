package com.barisgngr14.repositories;

import com.barisgngr14.entities.QuizSolveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizSolveDataRepository extends JpaRepository<QuizSolveData,String> {
}
