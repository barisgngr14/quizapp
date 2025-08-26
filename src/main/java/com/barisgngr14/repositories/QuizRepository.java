package com.barisgngr14.repositories;

import com.barisgngr14.entities.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quizzes, String> {
}
