package com.selekode.topaz.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.selekode.topaz.model.JournalEntry;

@RequestMapping("/journal")
@Controller
public class JournalEditEntryController {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

	@GetMapping("/editEntry/{id}")
	public String editJournalEntry(@PathVariable("id") Long id, Model model) {
	    // Fetch the journal entry by ID
	    JournalEntry journalEntry = loadJournalEntry(id);

	    // Add the journal entry to the model
	    model.addAttribute("journalEntry", journalEntry);

	    // Return the edit page
	    return "journal_editEntry";
	}
	
    // When the edit button on the form is clicked, it will send the data here
    @PostMapping("/updateEntry/{id}")
    public String updateEntry(@PathVariable("id") Long id, @ModelAttribute JournalEntry journalEntry) {
    	System.out.println("POST request to journal/updateEntry");
    	   	
        String updateQuery = "UPDATE journal SET title = ?, content = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            // Set values from the journalEntry object to the PreparedStatement
            preparedStatement.setString(1, journalEntry.getTitle());
            preparedStatement.setString(2, journalEntry.getContent());
            preparedStatement.setLong(3, journalEntry.getId());

            // Execute the update and get the number of affected rows
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
            	return "redirect_journal";
            };

        } catch (SQLException e) {
            e.printStackTrace();  // Print any exceptions for debugging
        }

		return "redirect_journal";
    }

	public JournalEntry loadJournalEntry(Long id) {
		JournalEntry journalEntry = null;
        String query = "SELECT id, date, title, content FROM journal WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the ID parameter
            statement.setLong(1, id);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    journalEntry = new JournalEntry(0, "", "", "");
                    journalEntry.setId(resultSet.getInt("id"));
                    journalEntry.setDate(resultSet.getString("date"));
                    journalEntry.setTitle(resultSet.getString("title"));
                    journalEntry.setContent(resultSet.getString("content"));
                    
                    System.out.println(journalEntry.getContent());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return journalEntry;
		
	}

}
