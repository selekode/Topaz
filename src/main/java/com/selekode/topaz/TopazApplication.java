package com.selekode.topaz;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.selekode.topaz.webview.*;
/**
 * Topaz | Journal, ToDo List and Habit Tracker
 * Created by Selekode 16/1/2025
 */
@SpringBootApplication
public class TopazApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopazApplication.class, args);
		TopazWebview.main(args);
	}
}
