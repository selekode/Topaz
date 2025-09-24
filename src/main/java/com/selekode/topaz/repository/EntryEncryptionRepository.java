package com.selekode.topaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selekode.topaz.model.EntryEncryptionKey;

@Repository
public interface EntryEncryptionRepository extends JpaRepository<EntryEncryptionKey, Long> {

}
