package com.selekode.topaz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EntryEncryptionKey {

    @Id
    private Long id = 1L; // Only one row for the key

    @Column(length = 512)
    private String encryptedKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}
    
    
}