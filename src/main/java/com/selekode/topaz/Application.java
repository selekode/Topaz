package com.selekode.topaz;

import com.selekode.topaz.bootstrap.AppBootstrap;
import com.selekode.topaz.webview.TopazWebview;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Topaz | Journal, emotion tracker, stats visualizer.
 * Take control of your life!
 *
 * Development started by Selekode 16/1/2025
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		AppBootstrap.initialize();
		SpringApplication.run(Application.class, args);
		TopazWebview.main(args);
	}

}