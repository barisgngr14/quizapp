package com.barisgngr14.repositories;

import com.barisgngr14.entities.QuizQuestions;
import com.barisgngr14.entities.QuizQuestionsID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestions, QuizQuestionsID> {
}
