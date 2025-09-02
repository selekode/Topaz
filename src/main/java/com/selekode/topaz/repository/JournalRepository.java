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
    
    @Query("SELECT FUNCTION('strftime', '%w', j.date) AS weekday, COUNT(j) AS entryCount " +
           "FROM JournalEntry j WHERE j.date BETWEEN ?1 AND ?2 GROUP BY weekday ORDER BY weekday")
    List<Object[]> findEntryCountPerDayDateRange(LocalDate startDate, LocalDate endDate);

    @Query("SELECT FUNCTION('strftime', '%w', j.date) AS weekday, COUNT(j) AS entryCount " +
           "FROM JournalEntry j GROUP BY weekday ORDER BY weekday")
    List<Object[]> findEntryCountPerDayAllTime();
    
	boolean existsByDate(LocalDate date);
}

