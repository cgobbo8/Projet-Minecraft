package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {


			
			Group root = new Group();
			Scene scene = new Scene(root,1000,600);

			int sizeL = (int) scene.getWidth();
			int sizeH = (int) scene.getHeight();

			Label la = new Label("Projet Minecraft");

			la.setLayoutX(sizeL /2 - la.getWidth() - 20);
			root.getChildren().addAll(la);

			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
