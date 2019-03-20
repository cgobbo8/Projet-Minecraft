package application;

import java.io.IOException;
import java.net.URL;

import application.MainClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class MainClass extends Application {


	@Override
	public void start(Stage primaryStage) {
		try {

			URL jeu = new URL("view/JeuVue.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(jeu);
			AnchorPane root = (AnchorPane) fxmlLoader.load();
			Scene scene = new Scene(root, 300, 250);
			primaryStage.setScene(scene);

		} catch (IOException ex) {
			System.err.println("Erreur au chargement: " + ex);
		}
		primaryStage.setTitle("Test FXML");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
