package com.barisgngr14.repositories;

import com.barisgngr14.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, String> {
    @Query("SELECT o FROM Option o WHERE o.question.questionId IN :questionIds")
    List<Option> findByQuestionIdIn(@Param("questionIds") List<String> questionIds);
}
