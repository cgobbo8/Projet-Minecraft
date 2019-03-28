package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class VuePrincipale extends Application {

	MediaPlayer clic; 
	public static double mousex;
	public static double mousey;
	public static double posIX;
	public static double posIY;
	public static double posFX;
	public static double posFY;
	
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
    	try {
    		Modele modl = new Modele();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("jeu.fxml"));
    		Controller ctrl = new Controller(modl);
    		loader.setController(ctrl);
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root, 1099, 619);

            
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("MineCraft");
            primaryStage.setScene(scene);
           
            root.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            	clic = new MediaPlayer(new Media(getClass().getResource("ressources/clic.mp3").toExternalForm()));
            	clic.setVolume(0.5) ;
            	clic.play();
            });
            
            posIX = root.getChildrenUnmodifiable().get(3).getLayoutX();
            posIX = root.getChildrenUnmodifiable().get(3).getLayoutY();

    		root.addEventFilter(MouseEvent.MOUSE_MOVED, e ->{
    			mousex = e.getX();
    			mousey = e.getY();
    			posFX = mousex - posIX;
    			posFY = mousey - posIY;
    		});

            
            primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


    public static void main(String[] args) {
        launch(args);
    }
}