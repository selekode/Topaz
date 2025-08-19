package com.selekode.topaz.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.repository.JournalRepository;
import com.selekode.topaz.utils.DatesHelper;

@Service
public class JournalService {
	
	private final JournalRepository repository;

    public JournalService(JournalRepository repository) {
        this.repository = repository;
    }

    public List<JournalEntry> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public JournalEntry getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public JournalEntry save(JournalEntry entry) {
    	if (entry.getDate() == null) {
            entry.setDate(LocalDate.now());
        }
        return repository.save(entry);
    }
    
    public JournalEntry updateJournalEntry(Long id, JournalEntry updatedEntry) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setDate(updatedEntry.getDate());
                    existing.setTitle(updatedEntry.getTitle());
                    existing.setContentGeneral(updatedEntry.getContentGeneral());
                    existing.setContentSaludFisica(updatedEntry.getContentSaludFisica());
                    existing.setContentBienestarMental(updatedEntry.getContentBienestarMental());
                    existing.setContentRelacionesSociales(updatedEntry.getContentRelacionesSociales());
                    existing.setContentCarreraProfesional(updatedEntry.getContentCarreraProfesional());
                    existing.setContentEstabilidadFinanciera(updatedEntry.getContentEstabilidadFinanciera());
                    existing.setContentCrecimientoPersonal(updatedEntry.getContentCrecimientoPersonal());
                    existing.setContentPasatiemposCreatividad(updatedEntry.getContentPasatiemposCreatividad());
                    existing.setContentEspiritualidadProposito(updatedEntry.getContentEspiritualidadProposito());
                    existing.setContentRecreacionDiversion(updatedEntry.getContentRecreacionDiversion());
                    existing.setContentContribucionLegado(updatedEntry.getContentContribucionLegado());
                    existing.setContentErroresCometidos(updatedEntry.getContentErroresCometidos());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Entry not found: " + id));
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }
}
