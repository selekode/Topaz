package com.selekode.topaz.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.Revision;
import com.selekode.topaz.model.ActivityPerDayOfWeekDTO;
import com.selekode.topaz.model.EmotionFrequencyDTO;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {
	@Query(value = "SELECT DISTINCT date FROM revision WHERE date IS NOT NULL ORDER BY date ASC", nativeQuery = true)
	List<Long> findAllDatesAsUnix();
	
	boolean existsByDate(LocalDate date);
}