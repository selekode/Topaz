package com.selekode.topaz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.selekode.topaz.model.JournalEntry;
@RequestMapping("/journal")
@Controller
public class JournalController {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

    @GetMapping("/load")
    public String loadPage(Model model) {
    	List<JournalEntry> journalEntries = loadAllEntries();
    	model.addAttribute("journalEntries", journalEntries); // Add journalEntries to the model
    	
        return "journal"; //Load journal.html
    }
    
    public List<JournalEntry> loadAllEntries() {      
        // SQL query to retrieve data from the table, ordered by date descending
        String sql = "SELECT id, date, title, content FROM journal ORDER BY date DESC";
        
    	List<JournalEntry> journalEntries = new ArrayList<>();
    	
    	// Connect to Database
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

               // Loop through the result set and add each row to the list
               while (rs.next()) {
                   int id = rs.getInt("id");
                   long date = rs.getLong("date");
                   String title = rs.getString("title");
                   String content = rs.getString("content");
                   
                   // Convert date from UNIX time to String
                   String dateStr = convertDateToString(date);

                   // Create a new JournalEntry object and add it to the list
                   JournalEntry journalEntry = new JournalEntry(id, dateStr, title, content);
                   journalEntries.add(journalEntry);
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
        
        System.out.println("Selected all rows from table journal");
    	
		return journalEntries;
    }

	
    @PostMapping("/deleteEntry")
    public String deleteEntry(@RequestParam("id") Long id) {
    	System.out.println("About to delete row with id: " + id);
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(DB_URL);
            
            // SQL query to delete the journal entry by ID
            String sql = "DELETE FROM journal WHERE id = ?";
            
            // Create a PreparedStatement to execute the query
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id); // Set the ID parameter
            
            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            
            // Check if the deletion was successful
            if (rowsAffected > 0) {
                System.out.println("Journal entry with ID " + id + " was deleted successfully.");
            } else {
                System.out.println("No journal entry found with ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting journal entry: " + e.getMessage());
        } finally {
            // Close resources to avoid memory leaks
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        
        return "redirect_journal";
    }

	public String convertDateToString(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
        Instant instant = Instant.ofEpochSecond(date);
        
        // Define the desired format (DD-MMM-YYYY)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy").withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);
		
		return dateStr;
	}
}
