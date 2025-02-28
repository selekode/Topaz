package com.selekode.topaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.selekode.topaz.model.RevisionEntry;

import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

@RequestMapping("/revision")
@Controller
public class RevisionNewEntryController {

	private static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

	@GetMapping("/addEntry")
	public String loadPage(Model model) {
		RevisionEntry revisionEntry = new RevisionEntry(0, "", "", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "", 
    	        false, false, false, false, false, false, false, false, false, false, 
    	        false, false, false, false, false);
		model.addAttribute("revisionEntry", revisionEntry);

		return "revision_addEntry"; // This will map to the 'revision_addEntry.html' Thymeleaf template
	}

	// When the save button on the form is clicked, it will send the data here
	@PostMapping("/saveEntry")
	public String saveNewEntry(@ModelAttribute RevisionEntry revisionEntry) {
		System.out.println("POST request to revision/saveEntry");
		
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String sql = "INSERT INTO revision (date, estadoEmocional, estadoEmocionalWhy, importanteParaMi, aprendidoSobreMi, valoracionDisciplina, valoracionOrden, valoracionImpulsividad, valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos, explicacionValoracion, objetivosPersonales, emocionAlegria, emocionTristeza, emocionIra, emocionMiedo, emocionAnsiedad, emocionAmor, emocionSorpresa, emocionVerguenza, emocionFrustracion, emocionSatisfaccion, emocionAburrimiento, emocionAmado, emocionConfianza, emocionAbrumado, emocionEsperanza) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setLong(1, generateUnixDate());
			    pstmt.setString(2, revisionEntry.getEstadoEmocional());
			    pstmt.setString(3, revisionEntry.getEstadoEmocionalWhy());
			    pstmt.setString(4, revisionEntry.getImportanteParaMi());
			    pstmt.setString(5, revisionEntry.getAprendidoSobreMi());
			    pstmt.setInt(6, revisionEntry.getValoracionDisciplina());
			    pstmt.setInt(7, revisionEntry.getValoracionOrden());
			    pstmt.setInt(8, revisionEntry.getValoracionImpulsividad());
			    pstmt.setInt(9, revisionEntry.getValoracionConstancia());
			    pstmt.setInt(10, revisionEntry.getValoracionTolerancia());
			    pstmt.setInt(11, revisionEntry.getValoracionControlPrepotencia());
			    pstmt.setInt(12, revisionEntry.getValoracionHonestidad());
			    pstmt.setInt(13, revisionEntry.getValoracionAceptacion());
			    pstmt.setInt(14, revisionEntry.getValoracionConsecucionObjetivos());
			    pstmt.setString(15, revisionEntry.getExplicacionValoracion());
			    pstmt.setString(16, revisionEntry.getObjetivosPersonales());
			    
			    // Set values for the emotion booleans
			    pstmt.setBoolean(17, revisionEntry.isEmocionAlegria());
			    pstmt.setBoolean(18, revisionEntry.isEmocionTristeza());
			    pstmt.setBoolean(19, revisionEntry.isEmocionIra());
			    pstmt.setBoolean(20, revisionEntry.isEmocionMiedo());
			    pstmt.setBoolean(21, revisionEntry.isEmocionAnsiedad());
			    pstmt.setBoolean(22, revisionEntry.isEmocionAmor());
			    pstmt.setBoolean(23, revisionEntry.isEmocionSorpresa());
			    pstmt.setBoolean(24, revisionEntry.isEmocionVerguenza());
			    pstmt.setBoolean(25, revisionEntry.isEmocionFrustracion());
			    pstmt.setBoolean(26, revisionEntry.isEmocionSatisfaccion());
			    pstmt.setBoolean(27, revisionEntry.isEmocionAburrimiento());
			    pstmt.setBoolean(28, revisionEntry.isEmocionAmado());
			    pstmt.setBoolean(29, revisionEntry.isEmocionConfianza());
			    pstmt.setBoolean(30, revisionEntry.isEmocionAbrumado());
			    pstmt.setBoolean(31, revisionEntry.isEmocionEsperanza());

				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect_revision";
	}

	public long generateUnixDate() {
		Instant now = Instant.now();
		long unixTime = now.getEpochSecond();

		return unixTime;
	}

}
