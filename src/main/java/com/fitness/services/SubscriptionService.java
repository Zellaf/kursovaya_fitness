package com.fitness.services;

import com.fitness.entities.History;
import com.fitness.entities.Subscription;
import com.fitness.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public List<Subscription> findAll(String searchKeyword) {
        return subscriptionRepository.searchKeyword(searchKeyword);
    }

    public void save(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public void del(Long id) {
        subscriptionRepository.deleteById(id);
    }

    public Subscription get(Long id) {
        return subscriptionRepository.findById(id).get();
    }

    public void truncate() {
        subscriptionRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);

        return subscription.isPresent();
    }
}
