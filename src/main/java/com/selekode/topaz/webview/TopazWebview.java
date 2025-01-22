package com.selekode.topaz.webview;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;

import javafx.application.Application;
import javafx.fxml.FXML;

public class TopazWebview extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		System.out.println("Launched Website");
		stage.setTitle("Topaz");

		Image icon = new Image(getClass().getResourceAsStream("/static/assets/brand/topaz-logo.png"));

		stage.getIcons().add(icon);

		WebView webView = new WebView();
		webView.getEngine().load("http://localhost:8080/");

		VBox vBox = new VBox(webView);
		VBox.setVgrow(webView, javafx.scene.layout.Priority.ALWAYS);

		Scene scene = new Scene(vBox, 1010, 600);

		stage.setScene(scene);
		stage.setMinWidth(1010);
		stage.show();
	}
}