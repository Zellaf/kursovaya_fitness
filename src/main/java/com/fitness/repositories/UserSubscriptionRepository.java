package com.fitness.repositories;

import com.fitness.entities.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {

    @Query("SELECT us FROM UserSubscription us WHERE CONCAT(us.user.id, ' ', us.subscription.id, ' ', LOWER(us.startDate), ' ', LOWER(us.endDate)) LIKE %?1%")
    List<UserSubscription> searchKeyword(String searchKeyword);

    @Query("SELECT us FROM UserSubscription us WHERE CONCAT(us.user.id, ' ', LOWER(us.subscription.name), ' ', LOWER(us.startDate), ' ', LOWER(us.endDate)) LIKE %?1%")
    List<UserSubscription> findByUserIdSearch(Long userId, String search_keyword);

    List<UserSubscription> findBySubscriptionId(Long id);
    
    List<UserSubscription> findByUserId(Long id);

}