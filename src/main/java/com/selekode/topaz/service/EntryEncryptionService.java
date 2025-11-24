package com.selekode.topaz.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.selekode.topaz.repository.EntryEncryptionRepository;
import com.selekode.topaz.model.EntryEncryptionKey;

@Service
public class EntryEncryptionService {
	private final EntryEncryptionRepository entryEncryptionRepository;

	public EntryEncryptionService(EntryEncryptionRepository entryEncryptionRepository) {
		this.entryEncryptionRepository = entryEncryptionRepository;
	}

	public void generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256); 
		SecretKey secretKey = keyGen.generateKey();

		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		
		EntryEncryptionKey keyEntity = new EntryEncryptionKey();
		keyEntity.setKey(encodedKey);
		saveKey(keyEntity);
	}

	public EntryEncryptionKey saveKey(EntryEncryptionKey keyEntity) {
		if (entryEncryptionRepository.count() > 0) {
            throw new IllegalStateException("Only one row is allowed in entry_encryption_key");
        }
		return entryEncryptionRepository.save(keyEntity);
	}

	public String encryptText(String plainText) throws Exception {
		EntryEncryptionKey keyEntity = entryEncryptionRepository.findById(1L)
				.orElseThrow(() -> new IllegalStateException("Encryption key not found"));

		byte[] decodedKey = Base64.getDecoder().decode(keyEntity.getKey());
		SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public String decryptText(String cipherText) throws Exception {
		EntryEncryptionKey keyEntity = entryEncryptionRepository.findById(1L)
				.orElseThrow(() -> new IllegalStateException("Encryption key not found"));

		byte[] decodedKey = Base64.getDecoder().decode(keyEntity.getKey());
		SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(decryptedBytes, "UTF-8");
	}
}
