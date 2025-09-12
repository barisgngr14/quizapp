package com.barisgngr14.repositories;

import com.barisgngr14.entities.QuizQuestion;
import com.barisgngr14.entities.QuizQuestionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, QuizQuestionID> {
    @Query(value = "SELECT question_id FROM quiz_questions WHERE quiz_id = :quizId", nativeQuery = true)
    List<String> findQuestionIdsByQuizId(@Param("quizId") String quizId);

}
