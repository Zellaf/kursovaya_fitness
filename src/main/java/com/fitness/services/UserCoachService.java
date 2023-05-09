package com.fitness.services;

import com.fitness.entities.UserCoach;
import com.fitness.repositories.UserCoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCoachService {

    @Autowired
    private UserCoachRepository userCoachRepository;

    public List<UserCoach> findAll() {
        return userCoachRepository.findAll();
    }

    public List<UserCoach> findByUserId(Long id) {
        return userCoachRepository.findByUserId(id);
    }

    public List<UserCoach> findByCoachId(Long id) {
        return userCoachRepository.findByCoachId(id);
    }

    public void save(UserCoach userCoach) {
        userCoachRepository.save(userCoach);
    }

    public void del(Long id) {
        userCoachRepository.deleteById(id);
    }

    public UserCoach get(Long id) {
        return userCoachRepository.findById(id).get();
    }

    public void truncate() {
        userCoachRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        Optional<UserCoach> userCoach = userCoachRepository.findById(id);

        return userCoach.isPresent();
    }

}
