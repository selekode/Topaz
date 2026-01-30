package com.selekode.topaz;

import com.selekode.topaz.database.InitializeDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.selekode.topaz.webview.*;
/**
 * Topaz | Journal, emotion tracker, stats visualizer. Take control of your life!
 * Development started by Selekode 16/1/2025
 */
@SpringBootApplication
public class TopazApplication {

	public static void main(String[] args) {
		KillPortIfOccupied.killPortIfOccupied(8080);
		SpringApplication.run(TopazApplication.class, args);
		TopazWebview.main(args);
	}
}
