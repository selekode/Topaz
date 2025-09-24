package com.selekode.topaz.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.repository.JournalRepository;

@Service
public class JournalService {

	private final JournalRepository journalRepository;
	private final EntryEncryptionService entryEncryptionService;

	public JournalService(JournalRepository journalRepository, EntryEncryptionService entryEncryptionService) {
		this.journalRepository = journalRepository;
		this.entryEncryptionService = entryEncryptionService;
	}

	private JournalEntry encryptEntry(JournalEntry entry) {
		try {
			entry.setTitle(entryEncryptionService.encryptText(entry.getTitle()));
			entry.setContentGeneral(entryEncryptionService.encryptText(entry.getContentGeneral()));
			entry.setContentSaludFisica(entryEncryptionService.encryptText(entry.getContentSaludFisica()));
			entry.setContentBienestarMental(entryEncryptionService.encryptText(entry.getContentBienestarMental()));
			entry.setContentRelacionesSociales(
					entryEncryptionService.encryptText(entry.getContentRelacionesSociales()));
			entry.setContentCarreraProfesional(
					entryEncryptionService.encryptText(entry.getContentCarreraProfesional()));
			entry.setContentEstabilidadFinanciera(
					entryEncryptionService.encryptText(entry.getContentEstabilidadFinanciera()));
			entry.setContentCrecimientoPersonal(
					entryEncryptionService.encryptText(entry.getContentCrecimientoPersonal()));
			entry.setContentPasatiemposCreatividad(
					entryEncryptionService.encryptText(entry.getContentPasatiemposCreatividad()));
			entry.setContentEspiritualidadProposito(
					entryEncryptionService.encryptText(entry.getContentEspiritualidadProposito()));
			entry.setContentRecreacionDiversion(
					entryEncryptionService.encryptText(entry.getContentRecreacionDiversion()));
			entry.setContentContribucionLegado(
					entryEncryptionService.encryptText(entry.getContentContribucionLegado()));
			entry.setContentErroresCometidos(entryEncryptionService.encryptText(entry.getContentErroresCometidos()));
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}
		return entry;
	}

	private JournalEntry decryptEntry(JournalEntry entry) {
		try {
			entry.setTitle(entryEncryptionService.decryptText(entry.getTitle()));
			entry.setContentGeneral(entryEncryptionService.decryptText(entry.getContentGeneral()));
			entry.setContentSaludFisica(entryEncryptionService.decryptText(entry.getContentSaludFisica()));
			entry.setContentBienestarMental(entryEncryptionService.decryptText(entry.getContentBienestarMental()));
			entry.setContentRelacionesSociales(
					entryEncryptionService.decryptText(entry.getContentRelacionesSociales()));
			entry.setContentCarreraProfesional(
					entryEncryptionService.decryptText(entry.getContentCarreraProfesional()));
			entry.setContentEstabilidadFinanciera(
					entryEncryptionService.decryptText(entry.getContentEstabilidadFinanciera()));
			entry.setContentCrecimientoPersonal(
					entryEncryptionService.decryptText(entry.getContentCrecimientoPersonal()));
			entry.setContentPasatiemposCreatividad(
					entryEncryptionService.decryptText(entry.getContentPasatiemposCreatividad()));
			entry.setContentEspiritualidadProposito(
					entryEncryptionService.decryptText(entry.getContentEspiritualidadProposito()));
			entry.setContentRecreacionDiversion(
					entryEncryptionService.decryptText(entry.getContentRecreacionDiversion()));
			entry.setContentContribucionLegado(
					entryEncryptionService.decryptText(entry.getContentContribucionLegado()));
			entry.setContentErroresCometidos(entryEncryptionService.decryptText(entry.getContentErroresCometidos()));
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}
		return entry;
	}

	/*
	 * public List<JournalEntry> getAll() { return
	 * journalRepository.findAll(Sort.by(Sort.Direction.DESC, "date")); }
	 */

	public List<JournalEntry> getAll() {
		return journalRepository.findAll(Sort.by(Sort.Direction.DESC, "date")).stream().map(this::decryptEntry)
				.collect(Collectors.toList());
	}

	/*
	 * public JournalEntry getById(Long id) { return
	 * journalRepository.findById(id).orElse(null); }
	 */

	public Optional<JournalEntry> findById(Long id) {
		return journalRepository.findById(id).map(this::decryptEntry);
	}

	public void save(JournalEntry entry) throws Exception {
		if (entry.getDate() == null) {
	        entry.setDate(LocalDate.now()); // default to today
	    }
		journalRepository.save(encryptEntry(entry));
	}

	public JournalEntry update(Long id, JournalEntry updatedJournalEntry) {
		
	    return journalRepository.findById(id).map(existing -> {
	        // preserve ID and date (if needed)
	        updatedJournalEntry.setId(existing.getId());

	        // encrypt before saving
	        JournalEntry encrypted = encryptEntry(updatedJournalEntry);
	        JournalEntry saved = journalRepository.save(encrypted);

	        // decrypt before returning
	        return decryptEntry(saved);
	    }).orElseThrow(() -> new IllegalArgumentException("Entry not found: " + id));
	}


	public void delete(Long id) {
		journalRepository.deleteById(id);
	}
}
