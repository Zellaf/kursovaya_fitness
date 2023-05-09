package com.fitness.repositories;

import com.fitness.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h WHERE CONCAT(h.user.id, ' ', LOWER(h.entryDate), ' ', LOWER(h.exitDate)) LIKE %?1%")
    List<History> search_keyword(String search_keyword);

    @Query("SELECT h FROM History h WHERE CONCAT(h.user.id, ' ', LOWER(h.entryDate), ' ', LOWER(h.exitDate)) LIKE %?2% AND h.user.id = ?1")
    List<History> findByUserIdSearch(Long userId, String search_keyword);

    List<History> findByUserId(Long Id);

}