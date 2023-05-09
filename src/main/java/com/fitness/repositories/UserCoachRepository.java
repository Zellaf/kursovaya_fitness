package com.fitness.repositories;

import com.fitness.entities.UserCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCoachRepository extends JpaRepository<UserCoach, Long> {

    @Query("SELECT uc FROM UserCoach uc WHERE CONCAT(uc.user.id, ' ', uc.coach.id) LIKE %?1%")
    List<UserCoach> searchKeyword(String searchKeyword);

    List<UserCoach> findByCoachId(Long id);

    List<UserCoach> findByUserId(Long id);

}