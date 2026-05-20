package com.selekode.topaz.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.Revision;
import com.selekode.topaz.repository.RevisionRepository;

@Service
public class RevisionService {

	private final RevisionRepository revisionRepository;
	private final EntryEncryptionService entryEncryptionService;

	public RevisionService(RevisionRepository revisionRepository, EntryEncryptionService entryEncryptionService) {
		this.revisionRepository = revisionRepository;
		this.entryEncryptionService = entryEncryptionService;
	}

	private Revision encryptRevision(Revision revision) {
		if (revision == null)
			return null;

		try {
			revision.setEstadoEmocional(entryEncryptionService.encryptText(revision.getEstadoEmocional()));
			revision.setEstadoEmocionalWhy(entryEncryptionService.encryptText(revision.getEstadoEmocionalWhy()));
			revision.setImportanteParaMi(entryEncryptionService.encryptText(revision.getImportanteParaMi()));
			revision.setAprendidoSobreMi(entryEncryptionService.encryptText(revision.getAprendidoSobreMi()));
			revision.setExplicacionValoracion(entryEncryptionService.encryptText(revision.getExplicacionValoracion()));
			revision.setObjetivosPersonales(entryEncryptionService.encryptText(revision.getObjetivosPersonales()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return revision;
	}

	private Revision decryptRevision(Revision revision) {
		if (revision == null)
			return null;

		try {
			revision.setEstadoEmocional(entryEncryptionService.decryptText(revision.getEstadoEmocional()));
			revision.setEstadoEmocionalWhy(entryEncryptionService.decryptText(revision.getEstadoEmocionalWhy()));
			revision.setImportanteParaMi(entryEncryptionService.decryptText(revision.getImportanteParaMi()));
			revision.setAprendidoSobreMi(entryEncryptionService.decryptText(revision.getAprendidoSobreMi()));
			revision.setExplicacionValoracion(entryEncryptionService.decryptText(revision.getExplicacionValoracion()));
			revision.setObjetivosPersonales(entryEncryptionService.decryptText(revision.getObjetivosPersonales()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return revision;
	}


	public List<Revision> getAll() {
		return revisionRepository.findAll().stream().map(this::decryptRevision)
				.sorted((r1, r2) -> r2.getDate().compareTo(r1.getDate())) // newest first
				.toList();
	}

	public Revision getById(Long id) {
		return revisionRepository.findById(id).map(this::decryptRevision).orElse(null);
	}

	public void save(Revision revisionEntry) {
		if (revisionEntry.getDate() == null) {
			revisionEntry.setDate(LocalDate.now());
		}
		revisionRepository.save(encryptRevision(revisionEntry));
	}

	public Revision update(Long id, Revision updatedEntry) {
		return revisionRepository.findById(id).map(existing -> {
			existing.setDate(updatedEntry.getDate());

			existing.setEstadoEmocional(updatedEntry.getEstadoEmocional());
			existing.setEstadoEmocionalWhy(updatedEntry.getEstadoEmocionalWhy());
			existing.setImportanteParaMi(updatedEntry.getImportanteParaMi());
			existing.setAprendidoSobreMi(updatedEntry.getAprendidoSobreMi());
			existing.setExplicacionValoracion(updatedEntry.getExplicacionValoracion());
			existing.setObjetivosPersonales(updatedEntry.getObjetivosPersonales());

			existing.setValoracionDisciplina(updatedEntry.getValoracionDisciplina());
			existing.setValoracionOrden(updatedEntry.getValoracionOrden());
			existing.setValoracionImpulsividad(updatedEntry.getValoracionImpulsividad());
			existing.setValoracionConstancia(updatedEntry.getValoracionConstancia());
			existing.setValoracionTolerancia(updatedEntry.getValoracionTolerancia());
			existing.setValoracionControlPrepotencia(updatedEntry.getValoracionControlPrepotencia());
			existing.setValoracionHonestidad(updatedEntry.getValoracionHonestidad());
			existing.setValoracionAceptacion(updatedEntry.getValoracionAceptacion());
			existing.setValoracionConsecucionObjetivos(updatedEntry.getValoracionConsecucionObjetivos());

			// 1. Core Emotions
			existing.setEmocionAlegria(updatedEntry.isEmocionAlegria());
			existing.setEmocionTristeza(updatedEntry.isEmocionTristeza());
			existing.setEmocionIra(updatedEntry.isEmocionIra());
			existing.setEmocionMiedo(updatedEntry.isEmocionMiedo());
			existing.setEmocionConfianza(updatedEntry.isEmocionConfianza());
			existing.setEmocionSorpresa(updatedEntry.isEmocionSorpresa());
			existing.setEmocionAnticipacion(updatedEntry.isEmocionAnticipacion());
			existing.setEmocionRechazo(updatedEntry.isEmocionRechazo());

			// 2. Mild Emotions
			existing.setEmocionSerenidad(updatedEntry.isEmocionSerenidad());
			existing.setEmocionMelancolia(updatedEntry.isEmocionMelancolia());
			existing.setEmocionFastidio(updatedEntry.isEmocionFastidio());
			existing.setEmocionAprension(updatedEntry.isEmocionAprension());
			existing.setEmocionAceptacion(updatedEntry.isEmocionAceptacion());
			existing.setEmocionDistraccion(updatedEntry.isEmocionDistraccion());
			existing.setEmocionInteres(updatedEntry.isEmocionInteres());
			existing.setEmocionAburrimiento(updatedEntry.isEmocionAburrimiento());

			// 3. Intense Emotions
			existing.setEmocionExtasis(updatedEntry.isEmocionExtasis());
			existing.setEmocionPenaDolor(updatedEntry.isEmocionPenaDolor());
			existing.setEmocionFuria(updatedEntry.isEmocionFuria());
			existing.setEmocionTerror(updatedEntry.isEmocionTerror());
			existing.setEmocionAdmiracion(updatedEntry.isEmocionAdmiracion());
			existing.setEmocionAsombro(updatedEntry.isEmocionAsombro());
			existing.setEmocionVigilancia(updatedEntry.isEmocionVigilancia());
			existing.setEmocionAsco(updatedEntry.isEmocionAsco());

			// 4. Complex / Reflective States
			existing.setEmocionAnsiedad(updatedEntry.isEmocionAnsiedad());
			existing.setEmocionFrustracion(updatedEntry.isEmocionFrustracion());
			existing.setEmocionVerguenza(updatedEntry.isEmocionVerguenza());
			existing.setEmocionEsperanza(updatedEntry.isEmocionEsperanza());
			existing.setEmocionOrgullo(updatedEntry.isEmocionOrgullo());
			existing.setEmocionAgobio(updatedEntry.isEmocionAgobio());

			// 5. Baseline State
			existing.setEmocionNeutral(updatedEntry.isEmocionNeutral());

			return revisionRepository.save(encryptRevision(existing));
		}).orElse(null);
	}

	public void delete(Long id) {
		revisionRepository.deleteById(id);
	}

}
