package com.fitness.repositories;

import com.fitness.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s WHERE CONCAT(LOWER(s.name), ' ', LOWER(s.description), ' ', s.cost) LIKE %?1%")
    List<Subscription> searchKeyword(String searchKeyword);

}