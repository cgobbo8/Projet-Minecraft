package application;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resources;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Controlleur implements Initializable {
	
	@FXML
	private Button btnplay = new Button();
	@FXML
	private Button settings = new Button();
	@FXML
	private Button exit = new Button();
	@FXML
	private Button settingCancel = new Button();
	@FXML
	private ImageView titre;
	@FXML
	private AnchorPane inventaire = new AnchorPane();
	@FXML
	private AnchorPane background = new AnchorPane();
	@FXML
	private AnchorPane bgopacity = new AnchorPane();
	@FXML
	private AnchorPane panelSetting = new AnchorPane();
	@FXML
	private AnchorPane menuprinc = new AnchorPane();
	@FXML
	private TabPane elementBasis = new TabPane();
	@FXML
	private BorderPane case1 = new BorderPane();
	@FXML
	private Tab tab1 = new Tab();
	@FXML
	private Tab tab2 = new Tab();
	@FXML
	private Tab tab3 = new Tab();
	@FXML
	private Tab tab4 = new Tab();
	@FXML
	private Tab tab5 = new Tab();
	private StackPane stackPane;
	
	
	MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("ressources/music2.mp3").toExternalForm())); 
	public boolean case1dep = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tab5.setTooltip(new Tooltip("Tous les éléments"));
		tab2.setTooltip(new Tooltip("Tous les outils"));
		tab3.setTooltip(new Tooltip("Toute la nourriture"));
		tab4.setTooltip(new Tooltip("Tous les armes"));
		tab1.setTooltip(new Tooltip("Blocs élementaires"));
	
	}
	
	public void draguer(Event e) {
		case1dep = true;
//		System.out.println(e);
//		double posX = case1.getLayoutX();
//		double posY = case1.getLayoutY();
//		
//		case1.setLayoutX(VuePrincipale.posFX);
//		case1.setLayoutY(VuePrincipale.posFY);
		this.case1.getChildren();
	}
	

	public void supp(Event e) throws InterruptedException {

		Path path = new Path();
		path.getElements().add(new MoveTo(307,titre.getLayoutY()+10));
		path.getElements().add(new LineTo(307, -100));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(2000));
		pathTransition.setPath(path);
		pathTransition.setNode(titre);
		pathTransition.play();
		
		FadeTransition title = new FadeTransition(Duration.millis(2000), titre);
		title.setFromValue(1.0);
		title.setToValue(0);
		title.play();
		FadeTransition btplay = new FadeTransition(Duration.millis(2000), btnplay);
		btplay.setFromValue(1.0);
		btplay.setToValue(0);
		btplay.play();
		FadeTransition btsettings = new FadeTransition(Duration.millis(2000), settings);
		btsettings.setFromValue(1.0);
		btsettings.setToValue(0);
		btsettings.play();
		FadeTransition btexit = new FadeTransition(Duration.millis(2000), exit);
		btexit.setFromValue(1.0);
		btexit.setToValue(0);
		btexit.play();
		FadeTransition opacity = new FadeTransition(Duration.millis(2000), bgopacity);
		opacity.setFromValue(0);
		opacity.setToValue(0.5);
		opacity.play();

		music.play();
		btsettings.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				titre.setVisible(false);
				btnplay.setVisible(false);
				settings.setVisible(false);
				exit.setVisible(false);
				
				inventaire.setVisible(true);
				FadeTransition ft4 = new FadeTransition(Duration.millis(1000), inventaire);
				ft4.setFromValue(0);
				ft4.setToValue(1);
				ft4.play();
			}
		});

	}
	
	public void setting(Event e) throws InterruptedException {
	    
		FadeTransition btplay = new FadeTransition(Duration.millis(2000), btnplay);
		btplay.setFromValue(1.0);
		btplay.setToValue(0);
		btplay.play();
		FadeTransition btsettings = new FadeTransition(Duration.millis(2000), settings);
		btsettings.setFromValue(1.0);
		btsettings.setToValue(0);
		btsettings.play();
		FadeTransition btexit = new FadeTransition(Duration.millis(2000), exit);
		btexit.setFromValue(1.0);
		btexit.setToValue(0);
		btexit.play();
		FadeTransition opacity = new FadeTransition(Duration.millis(2000), bgopacity);
		opacity.setFromValue(0);
		opacity.setToValue(0.5);
		opacity.play();

		btsettings.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				btnplay.setVisible(false);
				settings.setVisible(false);
				exit.setVisible(false);
				
				panelSetting.setVisible(true);
				FadeTransition ft4 = new FadeTransition(Duration.millis(1000), panelSetting);
				ft4.setFromValue(0);
				ft4.setToValue(1);
				ft4.play();
			}
		});
	}
	
	public void cancel() {
		FadeTransition panelSet = new FadeTransition(Duration.millis(2000), panelSetting);
		panelSet.setFromValue(1.0);
		panelSet.setToValue(0);
		panelSet.play();

		panelSet.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				btnplay.setVisible(true);
				settings.setVisible(true);
				exit.setVisible(true);
				panelSetting.setVisible(false);
				titre = new ImageView();
				titre.setVisible(true);


				FadeTransition btplay = new FadeTransition(Duration.millis(2000), btnplay);
				btplay.setFromValue(0);
				btplay.setToValue(1.0);
				btplay.play();
				FadeTransition btsettings = new FadeTransition(Duration.millis(2000), settings);
				btsettings.setFromValue(0);
				btsettings.setToValue(1.0);
				btsettings.play();
				FadeTransition btexit = new FadeTransition(Duration.millis(2000), exit);
				btexit.setFromValue(0);
				btexit.setToValue(1.0);
				btexit.play();
				FadeTransition opacity = new FadeTransition(Duration.millis(2000), bgopacity);
				opacity.setFromValue(0.5);
				opacity.setToValue(0);
				opacity.play();
			}
		});
	}
	
	public void exit(Event e) {
		System.exit(0);
	}

	
	
}
