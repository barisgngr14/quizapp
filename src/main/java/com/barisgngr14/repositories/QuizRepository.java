package com.barisgngr14.repositories;

import com.barisgngr14.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {
    @Query("SELECT q FROM Quiz q WHERE q.quizGroup.groupId IN :groupIds")
    List<Quiz> findAllByGroupIds(@Param("groupIds") List<String> groupIds);
}
