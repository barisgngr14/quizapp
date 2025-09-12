package com.barisgngr14.repositories;

import com.barisgngr14.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    @Query("SELECT q.questionText FROM Question q WHERE q.questionId IN :questionIds")
    List<String> findQuestionTexts(@Param("questionIds") List<String> questionIds);

    @Query("SELECT q.questionType FROM Question q WHERE q.questionId IN :questionIds")
    List<Question.Type> findQuestionTypes(@Param("questionIds") List<String> questionIds);
}
