package com.selekode.topaz.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.RevisionEntry;
import com.selekode.topaz.repository.RevisionRepository;

@Service
public class RevisionService {

    private final RevisionRepository revisionRepository;

    public RevisionService(RevisionRepository revisionRepository) {
        this.revisionRepository = revisionRepository;
    }

    public List<RevisionEntry> getAll() {
        return revisionRepository.findAll();
    }

    public RevisionEntry getById(Long id) {
        return revisionRepository.findById(id).orElse(null);
    }

    public void save(RevisionEntry revisionEntry) {
    	if (revisionEntry.getDate() == null) {
            revisionEntry.setDate(LocalDate.now());
        }
        revisionRepository.save(revisionEntry);
    }
    
    public RevisionEntry update(Long id, RevisionEntry updatedEntry) {
        return revisionRepository.findById(id)
                .map(existing -> {
                    // Update fields
                    existing.setDate(updatedEntry.getDate());
                    existing.setEstadoEmocional(updatedEntry.getEstadoEmocional());
                    existing.setEstadoEmocionalWhy(updatedEntry.getEstadoEmocionalWhy());
                    existing.setImportanteParaMi(updatedEntry.getImportanteParaMi());
                    existing.setAprendidoSobreMi(updatedEntry.getAprendidoSobreMi());
                    existing.setValoracionDisciplina(updatedEntry.getValoracionDisciplina());
                    existing.setValoracionOrden(updatedEntry.getValoracionOrden());
                    existing.setValoracionImpulsividad(updatedEntry.getValoracionImpulsividad());
                    existing.setValoracionConstancia(updatedEntry.getValoracionConstancia());
                    existing.setValoracionTolerancia(updatedEntry.getValoracionTolerancia());
                    existing.setValoracionControlPrepotencia(updatedEntry.getValoracionControlPrepotencia());
                    existing.setValoracionHonestidad(updatedEntry.getValoracionHonestidad());
                    existing.setValoracionAceptacion(updatedEntry.getValoracionAceptacion());
                    existing.setValoracionConsecucionObjetivos(updatedEntry.getValoracionConsecucionObjetivos());
                    existing.setExplicacionValoracion(updatedEntry.getExplicacionValoracion());
                    existing.setObjetivosPersonales(updatedEntry.getObjetivosPersonales());

                    // Emotions
                    existing.setEmocionAlegria(updatedEntry.isEmocionAlegria());
                    existing.setEmocionTristeza(updatedEntry.isEmocionTristeza());
                    existing.setEmocionIra(updatedEntry.isEmocionIra());
                    existing.setEmocionMiedo(updatedEntry.isEmocionMiedo());
                    existing.setEmocionAnsiedad(updatedEntry.isEmocionAnsiedad());
                    existing.setEmocionAmor(updatedEntry.isEmocionAmor());
                    existing.setEmocionSorpresa(updatedEntry.isEmocionSorpresa());
                    existing.setEmocionVerguenza(updatedEntry.isEmocionVerguenza());
                    existing.setEmocionFrustracion(updatedEntry.isEmocionFrustracion());
                    existing.setEmocionSatisfaccion(updatedEntry.isEmocionSatisfaccion());
                    existing.setEmocionAburrimiento(updatedEntry.isEmocionAburrimiento());
                    existing.setEmocionSerenidad(updatedEntry.isEmocionSerenidad());
                    existing.setEmocionConfianza(updatedEntry.isEmocionConfianza());
                    existing.setEmocionAbrumado(updatedEntry.isEmocionAbrumado());
                    existing.setEmocionEsperanza(updatedEntry.isEmocionEsperanza());

                    return revisionRepository.save(existing);
                })
                .orElse(null); // or throw exception if entry doesn't exist
    }

    public void delete(Long id) {
    	revisionRepository.deleteById(id);
    }

}
