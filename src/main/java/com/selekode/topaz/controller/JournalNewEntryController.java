package com.selekode.topaz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.selekode.topaz.model.JournalEntry;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
@RequestMapping("/journal")
@Controller
public class JournalNewEntryController {
	
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

    @GetMapping("/addEntry")
    public String loadPage(Model model) {
        JournalEntry journalEntry = new JournalEntry(0, "", "", "");
        model.addAttribute("journalEntry", journalEntry);

        return "journal_addEntry"; // This will map to the 'journal_addEntry.html' Thymeleaf template
    }
    
    // When the save button on the form is clicked, it will send the data here
    @PostMapping("/saveEntry")
    public String saveNewEntry(@ModelAttribute JournalEntry journalEntry) {
    	System.out.println("POST request to journal/saveEntry");
    	
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO journal (date, title, content) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, generateUnixDate());
                pstmt.setString(2, journalEntry.getTitle());
                pstmt.setString(3, journalEntry.getContent());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return "redirect_journal";
    }
    
    public long generateUnixDate() {
    	Instant now = Instant.now();
        long unixTime = now.getEpochSecond();
        
		return unixTime;
    }
    
}
