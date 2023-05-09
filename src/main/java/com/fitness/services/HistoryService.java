package com.fitness.services;

import com.fitness.entities.History;
import com.fitness.repositories.HistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public List<History> findAll(String search_keyword) {
        return historyRepository.search_keyword(search_keyword);
    }

    public List<History> findByUserId(Long id) {
        return historyRepository.findByUserId(id);
    }

    public List<History> findByUserId(Long id, String search_keyword) {
        return historyRepository.findByUserIdSearch(id, search_keyword);
    }

    public void save(History history) {
        try {
            historyRepository.save(history);
        } catch (Exception exception) {

        }
    }

    public void del(Long id) {
        historyRepository.deleteById(id);
    }

    public History get(Long id) {
        return historyRepository.findById(id).get();
    }

    public void truncate() {
        historyRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        Optional<History> history = historyRepository.findById(id);

        return history.isPresent();
    }

}
