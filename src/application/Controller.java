package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Craft.Type;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller implements Initializable {


	Modele modl = new Modele(new Inventaire());

	@FXML
	private Button btnplay,settings,exit,settingCancel,btnBlanc = new Button();
	@FXML
	private ImageView titre,imgFleche;
	@FXML
	private AnchorPane inventaire,background,bgopacity,panelSetting,menuprinc,matrice = new AnchorPane();
	@FXML
	private TabPane elementBasis = new TabPane();
	@FXML
	private Tab tab1,tab2,tab3,tab4,tab5 = new Tab();
	@FXML
	private AnchorPane pane1,pane2,pane3,pane4,inventaireFinal,bgnoir2,casesInv;
	@FXML
	private BorderPane c1,c2,c3,c4,c5,c6,c7,c8,c9;
	@FXML
	private Rectangle flecheBas;
	@FXML
	private Polygon flecheHaut;
	@FXML
	private Button test2;
	@FXML
	public AnchorPane btnFleche;
	@FXML
	public BorderPane craftFinal;


	ArrayList<BorderPane> l = new ArrayList<BorderPane>();
	
	ArrayList<BorderPane> listeBlocInv = new ArrayList<BorderPane>();
	ArrayList<BorderPane> listSoluces = new ArrayList<BorderPane>();
	
	ArrayList<Craft> listTest;

	Button btn;


	ArrayList<BorderPane> listeBloc = new ArrayList<BorderPane>();
	BorderPane bp;
	int caseX = 25;
	int caseY = 80;
	int indextable = 0;
	int indexInv = 0;
	Boolean flecheH = false;


	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("ressources/music2.mp3").toExternalForm())); 
	Craft blanc = new Craft("blanc", "blanc.png", new Craft[][] {{null,null,null},{null,null,null},{null,null,null},} ,Type.BASE,true) ;
	Craft craftTest = new Craft("bed", "bed.png",new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}},Type.BASE,true);

	Craft apple = new Craft("apple", "ressources/crafts/apple.png", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.NOURRITURE, true) ;
	Craft diamond = new Craft("diamond", "ressources/crafts/diamond.png", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BASE, true) ;

	Craft cobble = new Craft("cobblestone", "ressources/crafts/cobblestone.png", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BLOC, true) ;
	Craft four = new Craft("four", "ressources/crafts/furnace_front_on.png", new Craft [][] {{cobble,cobble,cobble},{cobble,blanc,cobble},{cobble,cobble,cobble}} , Type.BASE, false) ;


	public Controller() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tab5.setTooltip(new Tooltip("Tous les elements"));
		tab2.setTooltip(new Tooltip("Tous les outils"));
		tab3.setTooltip(new Tooltip("Toute la nourriture"));
		tab4.setTooltip(new Tooltip("Tous les armes"));
		tab1.setTooltip(new Tooltip("Blocs elementaires"));

		initialisationPane(pane1);
		initialisationPane(pane2);
		initialisationPane(pane3);
		initialisationPane(pane4);
		initialisationPaneInv(casesInv);

		l.add(c1);
		l.add(c2);
		l.add(c3);
		l.add(c4);
		l.add(c5);
		l.add(c6);
		l.add(c7);
		l.add(c8);
		l.add(c9);
		
		for (int i = 0; i < cobble.getParents().size(); i++) {
			System.out.println(four.getParents().get(i).getName());
		}

		for (int i = 0; i < l.size(); i++) {
			l.get(i).getBottom().setOpacity(0);
			l.get(i).setCenter(blanc.clone());
		}
		l.get(0).getBottom().setOpacity(1);

		try {
			this.modl.serialisation();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		PEUT ETRE SERVIALBLE POUR LA SUITE 
		/**
		tab1.setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {

			}
		});**/
	}

	public void initialisationPaneInv(AnchorPane a) {
		caseX = 50;
		caseY = 10;
		for (int i = 0; i < 6; i++) {
			caseX = 60;

			for (int j = 0; j < 10; j++) {
				bp = new BorderPane();
				bp.setId("caseCraft2");
				bp.setPrefSize(50, 50);
				bp.setLayoutX(caseX);
				bp.setLayoutY(caseY);
				caseX+=55;
				listeBlocInv.add(bp);
				a.getChildren().add(bp);
			}
			caseY+=55;
		}

	}
	
	
	public void initialisationPane(AnchorPane a) {
		caseX = 20;
		caseY = 80;
		for (int i = 0; i < 6; i++) {
			caseX = 25;

			for (int j = 0; j < 9; j++) {
				bp = new BorderPane();
				bp.setId("caseCraft");
				bp.setPrefSize(50, 50);
				bp.setLayoutX(caseX);
				bp.setLayoutY(caseY);
				bp.setOnMouseClicked(detectCraft);
				caseX+=55;
				listeBloc.add(bp);
				a.getChildren().add(bp);
			}
			caseY+=55;
		}

		for (int i = 0; i < 2; i++) {
			listeBloc.get(i).setCenter(craftTest.clone());
		}
		listeBloc.get(15).setCenter(cobble);
		listeBloc.get(16).setCenter(four);

	}


	EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {

		}
	};

	EventHandler<MouseEvent> detectCraft = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			click(e);

		}
	};

	public void cl(Event e) {
		Craft copy = blanc.clone();
		l.get(indextable).setCenter(copy);
		this.modl.ajoutCraftDansTable(blanc);
		indextable++;
		if (indextable == 9) {
			indextable = 0;
		}
		curseur();

	}

	private void testcraftcontroller() {
		if(this.modl.tableCraft[2][2] != null) {
			for (int i = 0; i < this.modl.tableCraft.length; i++) {
				for (int j = 0; j < this.modl.tableCraft.length; j++) {
					Craft resultat=this.modl.testCraft(i,j);
					resultat.setEstTrouve(true);
					craftFinal.setCenter(resultat.clone());
					this.modl.suppressionTable();
					for (int k = 0; k < l.size(); k++) {
						l.get(k).setCenter(null);
					}
					
					listTest = new ArrayList<Craft>();
					for (int k = 0; k < listeBlocInv.size(); k++) {
						listTest.add((Craft) listeBlocInv.get(i).getCenter());
					}
					if (!listTest.contains(resultat)) {
						listeBlocInv.get(indexInv).setCenter(resultat.clone());
						indexInv++;
					}else {
						System.out.println("h");
					}

					}
				}
			}

	}


	public void click(MouseEvent e) {

		if(e.getTarget().getClass()==Craft.class ) {

			this.modl.ajoutCraftDansTable((Craft) e.getTarget()); 
			Craft copyCraft = (Craft) e.getTarget();
			l.get(indextable).setCenter(copyCraft.clone());
			indextable++;
			if (indextable == 9) {
				indextable = 0;
			}

			curseur();
			testcraftcontroller();

			}
			
			String s = "";
			try {
				for (int j2 = 0; j2 < this.modl.tableCraft.length; j2++) {
					for (int k = 0; k < this.modl.tableCraft.length; k++) {
						s+= this.modl.tableCraft[j2][k].getName() + " ";
					}
				}
				System.out.println(s);
			} catch (NullPointerException e2) {
				// TODO: handle exception
			}

		}
	



	public void crafting(Craft c) {
		indextable = 0;
		curseur();
		for (int i = 0; i < l.size(); i++) {
			l.get(i).setCenter(blanc.clone() );
		}
		craftFinal.setCenter(c);
	}

	public void curseur() {
		for (int i = 0; i < l.size(); i++) {
			l.get(i).getBottom().setOpacity(0);
		}
		switch (indextable) {
		case 0:
			l.get(0).getBottom().setOpacity(1);
			break;
		case 1:
			l.get(1).getBottom().setOpacity(1);
			break;
		case 2:
			l.get(2).getBottom().setOpacity(1);
			break;
		case 3:
			l.get(3).getBottom().setOpacity(1);
			break;
		case 4:
			l.get(4).getBottom().setOpacity(1);
			break;
		case 5:
			l.get(5).getBottom().setOpacity(1);
			break;
		case 6:
			l.get(6).getBottom().setOpacity(1);
			break;
		case 7:
			l.get(7).getBottom().setOpacity(1);
			break;
		case 8:
			l.get(8).getBottom().setOpacity(1);
			break;
		default:
			break;
		}

	}

	public void ouvrirInv(Event e) {
		flecheH = !flecheH;
		inventaireFinal.setVisible(true);
		if (flecheH) {
			bgnoir2.setVisible(true);
			imgFleche.setRotate(90);
			Path path = new Path();
			path.getElements().add(new MoveTo(450,1000));
			path.getElements().add(new LineTo(450, 255));
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(2000));
			pathTransition.setPath(path);
			pathTransition.setNode(inventaireFinal);
			pathTransition.play();
			
			FadeTransition opacity = new FadeTransition(Duration.millis(2000), bgnoir2);
			opacity.setFromValue(0);
			opacity.setToValue(0.9);
			opacity.play();
			
		} else {
			imgFleche.setRotate(-90);
			Path path = new Path();
			path.getElements().add(new MoveTo(450, 255));
			path.getElements().add(new LineTo(450,1000));
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(2000));
			pathTransition.setPath(path);
			pathTransition.setNode(inventaireFinal);
			pathTransition.play();
			
			FadeTransition opacity = new FadeTransition(Duration.millis(2000), bgnoir2);
			opacity.setFromValue(0.9);
			opacity.setToValue(0);
			opacity.play();
			opacity.onFinishedProperty().set(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					bgnoir2.setVisible(false);
				}
			});
			
		}

	}

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
			((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
		}
	};



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

		//		music.play();
		btsettings.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				titre.setVisible(false);
				btnplay.setVisible(false);
				settings.setVisible(false);
				exit.setVisible(false);
				
				btnFleche.setVisible(true);
				FadeTransition ft5 = new FadeTransition(Duration.millis(1000), btnFleche);
				ft5.setFromValue(0);
				ft5.setToValue(0.7);
				ft5.play();
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
