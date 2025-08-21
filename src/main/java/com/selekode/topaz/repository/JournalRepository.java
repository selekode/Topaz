package com.selekode.topaz.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.selekode.topaz.model.JournalEntry;

@Repository
public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
	@Query(value = "SELECT DISTINCT date FROM journal WHERE date IS NOT NULL ORDER BY date ASC", nativeQuery = true)
	List<Long> findAllDatesAsUnix();

    /**
     * Check if there is any entry in a specific day range.
     * Example usage: existsByDatesBetween(startOfDay, endOfDay)
     */
    boolean existsByDateBetween(LocalDate today, LocalDate today2);
}

