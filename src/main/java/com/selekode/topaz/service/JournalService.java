package com.selekode.topaz.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.repository.JournalRepository;

@Service
public class JournalService {
	
	private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public JournalEntry getById(Long id) {
        return journalRepository.findById(id).orElse(null);
    }

    public JournalEntry save(JournalEntry journalEntry) {
    	if (journalEntry.getDate() == null) {
            journalEntry.setDate(LocalDate.now());
        }
        return journalRepository.save(journalEntry);
    }
    
    public JournalEntry update(Long id, JournalEntry updatedJournalEntry) {
        return journalRepository.findById(id)
                .map(existing -> {
                    existing.setDate(updatedJournalEntry.getDate());
                    existing.setTitle(updatedJournalEntry.getTitle());
                    existing.setContentGeneral(updatedJournalEntry.getContentGeneral());
                    existing.setContentSaludFisica(updatedJournalEntry.getContentSaludFisica());
                    existing.setContentBienestarMental(updatedJournalEntry.getContentBienestarMental());
                    existing.setContentRelacionesSociales(updatedJournalEntry.getContentRelacionesSociales());
                    existing.setContentCarreraProfesional(updatedJournalEntry.getContentCarreraProfesional());
                    existing.setContentEstabilidadFinanciera(updatedJournalEntry.getContentEstabilidadFinanciera());
                    existing.setContentCrecimientoPersonal(updatedJournalEntry.getContentCrecimientoPersonal());
                    existing.setContentPasatiemposCreatividad(updatedJournalEntry.getContentPasatiemposCreatividad());
                    existing.setContentEspiritualidadProposito(updatedJournalEntry.getContentEspiritualidadProposito());
                    existing.setContentRecreacionDiversion(updatedJournalEntry.getContentRecreacionDiversion());
                    existing.setContentContribucionLegado(updatedJournalEntry.getContentContribucionLegado());
                    existing.setContentErroresCometidos(updatedJournalEntry.getContentErroresCometidos());
                    return journalRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Entry not found: " + id));
    }

    
    public void delete(Long id) {
        journalRepository.deleteById(id);
    }
}
