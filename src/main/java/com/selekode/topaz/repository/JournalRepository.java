package com.selekode.topaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selekode.topaz.model.JournalEntry;

@Repository
public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
    // Spring will generate the implementation automatically
    // You can add custom queries here later if needed
}

