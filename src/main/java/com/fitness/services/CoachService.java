package com.fitness.services;

import com.fitness.entities.Coach;
import com.fitness.repositories.CoachRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public List<Coach> findByUserId(Long id) {
        return coachRepository.findByUserId(id);
    }

    public void save(Coach coach) {
        coachRepository.save(coach);
    }

    public void del(Long id) {
        coachRepository.deleteById(id);
    }

    public Coach get(Long id) {
        return coachRepository.findById(id).get();
    }

    public void truncate() {
        coachRepository.deleteAll();
    }

}
