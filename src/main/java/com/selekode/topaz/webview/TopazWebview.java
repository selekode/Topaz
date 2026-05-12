package com.selekode.topaz.webview;

import javafx.application.Platform;
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
		System.out.println("PROCESS: Initializing JavaFX WebView");

		String activePort = System.getProperty("app.active.port", "8080");
		Image icon = new Image(getClass().getResourceAsStream("/static/images/topaz-logo.png"));

		stage.setTitle("Topaz");
		stage.getIcons().add(icon);

		WebView webView = new WebView();
		webView.setZoom(1.0);
		webView.setStyle("-fx-font-smoothing-type: lcd;");
		webView.setZoom(1.05);

		webView.getEngine().load("http://localhost:" + activePort + "/login");

		VBox vBox = new VBox(webView);
		VBox.setVgrow(webView, javafx.scene.layout.Priority.ALWAYS);

		Scene scene = new Scene(vBox, 1280, 720, true);
		scene.setCamera(new PerspectiveCamera(false));

		stage.setScene(scene);
		stage.setMinWidth(1010);
		stage.show();
		System.out.println("PROCESS: Opened JavaFX WebView on " + "http://localhost:" + activePort + "/login");

		stage.setOnCloseRequest(event -> {
			System.out.println("PROCESS: Closing Topaz...");
			Platform.exit();
			System.exit(0);
		});
	}
}