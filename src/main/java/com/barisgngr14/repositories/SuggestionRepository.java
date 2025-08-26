package com.barisgngr14.repositories;

import com.barisgngr14.entities.Suggestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestions, String> {
}
