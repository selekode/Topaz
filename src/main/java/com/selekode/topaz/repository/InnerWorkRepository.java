package com.selekode.topaz.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.selekode.topaz.model.InnerWork;

@Repository
public interface InnerWorkRepository extends JpaRepository<InnerWork, Long> {
    List<InnerWork> findAllByOrderByDateDesc();
    
    int countByDateBetween(Long startDate, Long endDate);

	boolean existsByDate(LocalDate date);

}
