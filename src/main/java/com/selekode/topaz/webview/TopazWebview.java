package com.selekode.topaz.webview;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.application.Application;

public class TopazWebview extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		System.out.println("Launched Website");
		stage.setTitle("Topaz");

		Image icon = new Image(getClass().getResourceAsStream("/static/images/topaz-logo.png"));

		stage.getIcons().add(icon);

		WebView webView = new WebView();
		webView.getEngine().load("http://localhost:8080/journal/load");

		VBox vBox = new VBox(webView);
		VBox.setVgrow(webView, javafx.scene.layout.Priority.ALWAYS);

		Scene scene = new Scene(vBox, 1010, 700, true);
		scene.setCamera(new PerspectiveCamera(false));

		stage.setScene(scene);
		stage.setMinWidth(1010);
		stage.show();
	}
}