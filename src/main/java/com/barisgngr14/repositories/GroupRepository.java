package com.barisgngr14.repositories;

import com.barisgngr14.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    @Query(value = "SELECT group_id FROM group_members WHERE user_id = :userId", nativeQuery = true)
    List<String> findGroupIdsByUserId(@Param("userId") String userId);
}
