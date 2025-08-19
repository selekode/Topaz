package com.selekode.topaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selekode.topaz.model.RevisionEntry;

@Repository
public interface RevisionRepository extends JpaRepository<RevisionEntry, Long> {
    // Spring will generate the implementation automatically
    // You can add custom queries here later if needed
}