package com.barisgngr14.repositories;

import com.barisgngr14.entities.QuizQuestion;
import com.barisgngr14.entities.QuizQuestionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, QuizQuestionID> {
}
