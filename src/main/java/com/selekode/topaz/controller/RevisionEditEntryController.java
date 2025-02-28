package com.selekode.topaz.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selekode.topaz.model.RevisionEntry;

@RequestMapping("/revision")
@Controller
public class RevisionEditEntryController {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";
    
	public RevisionEntry loadEntry(Long id) {
		RevisionEntry revisionEntry = null;
		String query = "SELECT id, date, estadoEmocional, estadoEmocionalWhy, importanteParaMi, "
	             + "aprendidoSobreMi, valoracionDisciplina, valoracionOrden, valoracionImpulsividad, "
	             + "valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia, "
	             + "valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos, "
	             + "explicacionValoracion, objetivosPersonales, emocionAlegria, emocionTristeza, "
	             + "emocionIra, emocionMiedo, emocionAnsiedad, emocionAmor, emocionSorpresa, "
	             + "emocionVerguenza, emocionFrustracion, emocionSatisfaccion, emocionAburrimiento, "
	             + "emocionAmado, emocionConfianza, emocionAbrumado, emocionEsperanza "
	             + "FROM revision WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the ID parameter
            statement.setLong(1, id);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
            	if (resultSet.next()) {
            	    // Create a revisionEntry object and load it with the data from the DB
            	    revisionEntry = new RevisionEntry(0, "", "", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "", 
            	        false, false, false, false, false, false, false, false, false, false, 
            	        false, false, false, false, false);
            	    
            	    revisionEntry.setId(resultSet.getInt("id"));
            	    revisionEntry.setDate(resultSet.getString("date"));   
            	    revisionEntry.setEstadoEmocional(resultSet.getString("estadoEmocional"));   
            	    revisionEntry.setEstadoEmocionalWhy(resultSet.getString("estadoEmocionalWhy"));   
            	    revisionEntry.setImportanteParaMi(resultSet.getString("importanteParaMi"));   
            	    revisionEntry.setAprendidoSobreMi(resultSet.getString("aprendidoSobreMi"));   
            	    revisionEntry.setValoracionDisciplina(resultSet.getInt("valoracionDisciplina"));   
            	    revisionEntry.setValoracionOrden(resultSet.getInt("valoracionOrden"));   
            	    revisionEntry.setValoracionImpulsividad(resultSet.getInt("valoracionImpulsividad"));   
            	    revisionEntry.setValoracionConstancia(resultSet.getInt("valoracionConstancia"));   
            	    revisionEntry.setValoracionTolerancia(resultSet.getInt("valoracionTolerancia"));   
            	    revisionEntry.setValoracionControlPrepotencia(resultSet.getInt("valoracionControlPrepotencia"));   
            	    revisionEntry.setValoracionHonestidad(resultSet.getInt("valoracionHonestidad"));   
            	    revisionEntry.setValoracionAceptacion(resultSet.getInt("valoracionAceptacion"));   
            	    revisionEntry.setValoracionConsecucionObjetivos(resultSet.getInt("valoracionConsecucionObjetivos"));   
            	    revisionEntry.setExplicacionValoracion(resultSet.getString("explicacionValoracion"));   
            	    revisionEntry.setObjetivosPersonales(resultSet.getString("objetivosPersonales"));   

            	    // Set emotion fields
            	    revisionEntry.setEmocionAlegria(resultSet.getBoolean("emocionAlegria"));
            	    revisionEntry.setEmocionTristeza(resultSet.getBoolean("emocionTristeza"));
            	    revisionEntry.setEmocionIra(resultSet.getBoolean("emocionIra"));
            	    revisionEntry.setEmocionMiedo(resultSet.getBoolean("emocionMiedo"));
            	    revisionEntry.setEmocionAnsiedad(resultSet.getBoolean("emocionAnsiedad"));
            	    revisionEntry.setEmocionAmor(resultSet.getBoolean("emocionAmor"));
            	    revisionEntry.setEmocionSorpresa(resultSet.getBoolean("emocionSorpresa"));
            	    revisionEntry.setEmocionVerguenza(resultSet.getBoolean("emocionVerguenza"));
            	    revisionEntry.setEmocionFrustracion(resultSet.getBoolean("emocionFrustracion"));
            	    revisionEntry.setEmocionSatisfaccion(resultSet.getBoolean("emocionSatisfaccion"));
            	    revisionEntry.setEmocionAburrimiento(resultSet.getBoolean("emocionAburrimiento"));
            	    revisionEntry.setEmocionAmado(resultSet.getBoolean("emocionAmado"));
            	    revisionEntry.setEmocionConfianza(resultSet.getBoolean("emocionConfianza"));
            	    revisionEntry.setEmocionAbrumado(resultSet.getBoolean("emocionAbrumado"));
            	    revisionEntry.setEmocionEsperanza(resultSet.getBoolean("emocionEsperanza"));
            	}

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // The object, now filled with data, is returned to the model
        return revisionEntry;
		
	}

	@GetMapping("/editEntry/{id}")
	public String loadPage(@PathVariable("id") Long id, Model model) {
	    // Fetch the journal entry by ID
	    RevisionEntry revisionEntry = loadEntry(id);

	    // Add the journal entry to the model
	    model.addAttribute("revisionEntry", revisionEntry);

	    // Return the edit page
	    return "revision_editEntry";
	}
	
    // When the edit button on the form is clicked, it will send the data here
    @PostMapping("/updateEntry/{id}")
    public String updateEntry(@PathVariable("id") Long id, @ModelAttribute RevisionEntry revisionEntry) {
    	System.out.println("POST request to revision/updateEntry");
    	   	
    	String updateQuery = "UPDATE revision SET estadoEmocional = ?, estadoEmocionalWhy = ?, importanteParaMi = ?, "
    	        + "aprendidoSobreMi = ?, valoracionDisciplina = ?, valoracionOrden = ?, valoracionImpulsividad = ?, "
    	        + "valoracionConstancia = ?, valoracionTolerancia = ?, valoracionControlPrepotencia = ?, "
    	        + "valoracionHonestidad = ?, valoracionAceptacion = ?, valoracionConsecucionObjetivos = ?, "
    	        + "explicacionValoracion = ?, objetivosPersonales = ?, "
    	        + "emocionAlegria = ?, emocionTristeza = ?, emocionIra = ?, emocionMiedo = ?, "
    	        + "emocionAnsiedad = ?, emocionAmor = ?, emocionSorpresa = ?, emocionVerguenza = ?, "
    	        + "emocionFrustracion = ?, emocionSatisfaccion = ?, emocionAburrimiento = ?, "
    	        + "emocionAmado = ?, emocionConfianza = ?, emocionAbrumado = ?, emocionEsperanza = ? "
    	        + "WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {

            // Set values from the journalEntry object to the PreparedStatement
			pstmt.setString(1, revisionEntry.getEstadoEmocional());
			pstmt.setString(2, revisionEntry.getEstadoEmocionalWhy());
			pstmt.setString(3, revisionEntry.getImportanteParaMi());
			pstmt.setString(4, revisionEntry.getAprendidoSobreMi());
			pstmt.setInt(5, revisionEntry.getValoracionDisciplina());
			pstmt.setInt(6, revisionEntry.getValoracionOrden());
			pstmt.setInt(7, revisionEntry.getValoracionImpulsividad());
			pstmt.setInt(8, revisionEntry.getValoracionConstancia());
			pstmt.setInt(9, revisionEntry.getValoracionTolerancia());
			pstmt.setInt(10, revisionEntry.getValoracionControlPrepotencia());
			pstmt.setInt(11, revisionEntry.getValoracionHonestidad());
			pstmt.setInt(12, revisionEntry.getValoracionAceptacion());
			pstmt.setInt(13, revisionEntry.getValoracionConsecucionObjetivos());
			pstmt.setString(14, revisionEntry.getExplicacionValoracion());
			pstmt.setString(15, revisionEntry.getObjetivosPersonales());
		    pstmt.setBoolean(16, revisionEntry.isEmocionAlegria());
		    pstmt.setBoolean(17, revisionEntry.isEmocionTristeza());
		    pstmt.setBoolean(18, revisionEntry.isEmocionIra());
		    pstmt.setBoolean(19, revisionEntry.isEmocionMiedo());
		    pstmt.setBoolean(20, revisionEntry.isEmocionAnsiedad());
		    pstmt.setBoolean(21, revisionEntry.isEmocionAmor());
		    pstmt.setBoolean(22, revisionEntry.isEmocionSorpresa());
		    pstmt.setBoolean(23, revisionEntry.isEmocionVerguenza());
		    pstmt.setBoolean(24, revisionEntry.isEmocionFrustracion());
		    pstmt.setBoolean(25, revisionEntry.isEmocionSatisfaccion());
		    pstmt.setBoolean(26, revisionEntry.isEmocionAburrimiento());
		    pstmt.setBoolean(27, revisionEntry.isEmocionAmado());
		    pstmt.setBoolean(28, revisionEntry.isEmocionConfianza());
		    pstmt.setBoolean(29, revisionEntry.isEmocionAbrumado());
		    pstmt.setBoolean(30, revisionEntry.isEmocionEsperanza());
		    pstmt.setInt(31, revisionEntry.getId()); // Make sure this is the last parameter

            // Execute the update and get the number of affected rows
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
            	return "redirect_revision";
            };

        } catch (SQLException e) {
            e.printStackTrace();  // Print any exceptions for debugging
        }

		return "redirect_revision";
    }

}
