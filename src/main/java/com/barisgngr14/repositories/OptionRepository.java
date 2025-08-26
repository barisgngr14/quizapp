package com.barisgngr14.repositories;

import com.barisgngr14.entities.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Options, String> {
}
