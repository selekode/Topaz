package com.selekode.topaz.api;

import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.service.JournalService;
import com.selekode.topaz.dto.JournalEntryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/journal")
public class JournalRestController {

    private final JournalService journalService;

    public JournalRestController(JournalService journalService) {
        this.journalService = journalService;
    }

    // POST new entry
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntryDTO dto) {
        JournalEntry entry = new JournalEntry();

        entry.setTitle(dto.getTitle());
        entry.setContentGeneral(dto.getContentGeneral());
        entry.setContentSaludFisica(dto.getContentSaludFisica());
        entry.setContentBienestarMental(dto.getContentBienestarMental());
        entry.setContentRelacionesSociales(dto.getContentRelacionesSociales());
        entry.setContentCarreraProfesional(dto.getContentCarreraProfesional());
        entry.setContentEstabilidadFinanciera(dto.getContentEstabilidadFinanciera());
        entry.setContentCrecimientoPersonal(dto.getContentCrecimientoPersonal());
        entry.setContentPasatiemposCreatividad(dto.getContentPasatiemposCreatividad());
        entry.setContentEspiritualidadProposito(dto.getContentEspiritualidadProposito());
        entry.setContentRecreacionDiversion(dto.getContentRecreacionDiversion());
        entry.setContentContribucionLegado(dto.getContentContribucionLegado());
        entry.setContentErroresCometidos(dto.getContentErroresCometidos());

        entry.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());

        JournalEntry saved = journalService.save(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
