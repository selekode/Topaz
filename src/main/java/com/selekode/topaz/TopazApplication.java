package com.selekode.topaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.selekode.topaz.database.DatabaseCreator;
import com.selekode.topaz.webview.*;
/**
 * Topaz | Journal, emotion tracker, stats visualizer. Take control of your life!
 * Development started by Selekode 16/1/2025
 */
@SpringBootApplication
public class TopazApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopazApplication.class, args);
		DatabaseCreator.main(args);
		TopazWebview.main(args);
	}
}
