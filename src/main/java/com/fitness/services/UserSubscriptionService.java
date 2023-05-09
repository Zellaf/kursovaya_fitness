package com.fitness.services;

import com.fitness.entities.UserSubscription;
import com.fitness.repositories.UserSubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UserSubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    public List<UserSubscription> findAll() {
        return userSubscriptionRepository.findAll();
    }

    public List<UserSubscription> findAll(String searchKeyword) {
        return userSubscriptionRepository.searchKeyword(searchKeyword);
    }

    public List<UserSubscription> findByUserId (Long id) {
        return userSubscriptionRepository.findByUserId(id);
    }
    public List<UserSubscription> findByUserId (Long id, String search_keyword) {
        return userSubscriptionRepository.findByUserIdSearch(id, search_keyword);
    }

    public List<UserSubscription> findBySubscriptionId (Long id) {
        return userSubscriptionRepository.findBySubscriptionId(id);
    }

    public void save(UserSubscription userSubscription) {
        userSubscriptionRepository.save(userSubscription);
    }

    public void del(Long id) {
        userSubscriptionRepository.deleteById(id);
    }

    public UserSubscription get(Long id) {
        return userSubscriptionRepository.findById(id).get();
    }

    public void truncate() {
        userSubscriptionRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        Optional<UserSubscription> userSubscription = userSubscriptionRepository.findById(id);

        return userSubscription.isPresent();
    }

}
