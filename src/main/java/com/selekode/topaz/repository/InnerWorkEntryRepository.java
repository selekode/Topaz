package com.selekode.topaz.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.selekode.topaz.model.InnerWorkEntry;

@Repository
public interface InnerWorkEntryRepository extends JpaRepository<InnerWorkEntry, Long> {
	@Query("SELECT DISTINCT i.date FROM InnerWorkEntry i ORDER BY i.date ASC")
    List<Long> findAllDates();

    /**
     * Check if there is any entry in a specific day range.
     * Example usage: existsByDatesBetween(startOfDay, endOfDay)
     */
    boolean existsByDateBetween(LocalDate today, LocalDate today2);
}
