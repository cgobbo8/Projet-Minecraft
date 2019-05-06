package application;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
	private ImageView titre,imgFleche, imageCraft,endgame;
	@FXML
	private AnchorPane noirVideo,paneVideo,inventaire,background,bgopacity,panelSetting,menuprinc,matrice = new AnchorPane();
	@FXML
	private TabPane elementBasis = new TabPane();
	@FXML
	private Tab tab1,tab2,tab3,tab4,tab5 = new Tab();
	@FXML
	private AnchorPane pane1,pane2,pane3,pane4,pane5,inventaireFinal,bgnoir2,casesInv;
	@FXML
	private BorderPane c1,c2,c3,c4,c5,c6,c7,c8,c9;
	@FXML
	private BorderPane d1,d2,d3,d4,d5,d6,d7,d8,d9;
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
	@FXML
	public Label scoreCraft, nomCraft, labelTest, affScore;
	@FXML
	public ProgressBar pg;
	@FXML
	public MediaView video;
	public MediaPlayer mp;
	public Media me;


	ArrayList<BorderPane> l = new ArrayList<BorderPane>();
	ArrayList<BorderPane> matriceInv = new ArrayList<BorderPane>();

	ArrayList<BorderPane> listeBlocInv = new ArrayList<BorderPane>();
	ArrayList<BorderPane> listSoluces = new ArrayList<BorderPane>();

	ArrayList<Craft> listeCraft = new ArrayList<Craft>();
	int scoreFinal = 40; //listeCraft.size();


	ArrayList<Craft> listTest;

	Button btn;


	ArrayList<BorderPane> listeToutElement = new ArrayList<BorderPane>();

	ArrayList<BorderPane> listeTypeBloc = new ArrayList<BorderPane>();
	ArrayList<BorderPane> listeTypeCombat = new ArrayList<BorderPane>();
	ArrayList<BorderPane> listeTypeOutil = new ArrayList<BorderPane>();
	ArrayList<BorderPane> listeTypeNourriture = new ArrayList<BorderPane>();
	ArrayList<BorderPane> listeTypeDeco = new ArrayList<BorderPane>();
	ArrayList<BorderPane> listeTypeBase = new ArrayList<BorderPane>();
	
	ArrayList<String> listeCraftInventaire = new ArrayList<String>();


	BorderPane bp;
	int caseX = 25;
	int caseY = 80;
	int indextable = 0;
	int indexInv = 0;
	public int score = 0;
	Boolean flecheH = false;


	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("ressources/music.mp3").toExternalForm())); 
	MediaPlayer avengers = new MediaPlayer(new Media(getClass().getResource("ressources/avengers.mp3").toExternalForm())); 


	Craft blanc = new Craft("blanc", "blanc.png", new Craft[][] {{null,null,null},{null,null,null},{null,null,null},} ,Type.BLOC,true) ;


	public Controller() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tab5.setTooltip(new Tooltip("Tous les elements"));
		tab2.setTooltip(new Tooltip("Tous les outils"));
		tab3.setTooltip(new Tooltip("Toute la nourriture"));
		tab4.setTooltip(new Tooltip("Tous les armes"));
		tab1.setTooltip(new Tooltip("Blocs elementaires"));

		l.add(c1);l.add(c2);l.add(c3);l.add(c4);l.add(c5);l.add(c6);l.add(c7);l.add(c8);l.add(c9);

		matriceInv.add(d1);matriceInv.add(d2);matriceInv.add(d3);matriceInv.add(d4);matriceInv.add(d5);matriceInv.add(d6);matriceInv.add(d7);matriceInv.add(d8);matriceInv.add(d9);


		for (int i = 0; i < l.size(); i++) {
			l.get(i).getBottom().setOpacity(0);
			l.get(i).setCenter(blanc.clone());
		}
		l.get(0).getBottom().setOpacity(1);

		try {
			this.modl.inventairePrincipal.addCraft(blanc);
			this.modl.serialisation();

			for (int i = 0; i < this.modl.inventairePrincipal.getListeInventaire().size(); i++) {
				System.out.println(this.modl.inventairePrincipal.getListeInventaire().get(i).getName());
			}

		} catch (NullPointerException e) {
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initialisationPane(pane1,listeTypeBase);
		initialisationPane(pane2,listeTypeOutil);
		initialisationPane(pane3,listeTypeNourriture);
		initialisationPane(pane4,listeTypeCombat);
		initialisationPane(pane5,listeToutElement);
		initialisationPaneInv(casesInv);

		for (int i = 0; i < this.modl.getInventairePrincipal().getListeInventaire().size(); i++) {
			listeToutElement.get(i).setCenter(this.modl.getInventairePrincipal().getListeInventaire().get(i).clone());
		}
		
		for (int i = 0; i < this.modl.getInventairePrincipal().getBase().size(); i++) {
			if (this.modl.getInventairePrincipal().getBase().get(i).getName() != "blanc") {
				listeTypeBase.get(i).setCenter(this.modl.getInventairePrincipal().getBase().get(i));
			}
			
		}
		
		for (int i = 0; i < this.modl.getInventairePrincipal().getOutil().size(); i++) {
			listeTypeOutil.get(i).setCenter(this.modl.getInventairePrincipal().getOutil().get(i));
		}
		
		for (int i = 0; i < this.modl.getInventairePrincipal().getNourriture().size(); i++) {
			listeTypeNourriture.get(i).setCenter(this.modl.getInventairePrincipal().getNourriture().get(i));
		}
		
		for (int i = 0; i < this.modl.getInventairePrincipal().getCombat().size(); i++) {
			listeTypeCombat.get(i).setCenter(this.modl.getInventairePrincipal().getCombat().get(i));
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
				bp.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event e) {
						Craft c = (Craft) e.getTarget();
						String s1 = c.getName().intern();
						String s2 = new String("four");
						s2 = s2.intern();
						if (s1 == s2) {
							music.stop();
							noirVideo.setVisible(true);
							
							FadeTransition opacity = new FadeTransition(Duration.millis(2000), noirVideo);
							opacity.setFromValue(0);
							opacity.setToValue(1);
							opacity.play();
							opacity.setOnFinished(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									paneVideo.setVisible(true);
									String path = new File("src/application/ressources/snap.mp4").getAbsolutePath();
									mp = new MediaPlayer(new Media(new File(path).toURI().toString()));
									video.setMediaPlayer(mp);
									mp.setAutoPlay(true);
									
									endgame.setVisible(true);
									FadeTransition opacity = new FadeTransition(Duration.millis(4000), endgame);
									opacity.setFromValue(0);
									opacity.setToValue(0);
									opacity.play();
									avengers.play();
									opacity.setOnFinished(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent event) {
											endgame.setVisible(true);
											
											FadeTransition opacity = new FadeTransition(Duration.millis(2000), endgame);
											opacity.setFromValue(0);
											opacity.setToValue(1);
											opacity.play();
											
										}
									});
								}
							});
							

						}
						System.out.println(c.getName());
						nomCraft.setText(c.getName());

						Image img = new Image(getClass().getResourceAsStream(c.ip));
						imageCraft.setImage(img);

						Craft[][] matriceCraft = c.getMatrice();

						ArrayList<Craft> lC = new ArrayList<Craft>();
						
						for (int j2 = 0; j2 < matriceCraft.length; j2++) {
							for (int j3 = 0; j3 < matriceCraft.length; j3++) {
								lC.add(matriceCraft[j2][j3]);
							}
						}
						
						for (int j2 = 0; j2 < matriceInv.size(); j2++) {
							matriceInv.get(j2).setCenter(null);
						}
						for (int j2 = 0; j2 < lC.size(); j2++) {
							if (lC.get(j2).getName() != "blanc") {
								matriceInv.get(j2).setCenter(lC.get(j2).clone());	
							}
						}
						
						
					}
				});
				listeBlocInv.add(bp);
				a.getChildren().add(bp);
			}
			caseY+=55;
		}
	}

	public void initialisationPane(AnchorPane a,ArrayList<BorderPane> l) {
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
				l.add(bp);
				a.getChildren().add(bp);
			}
			caseY+=55;
		}

//		for (int i = 0; i < this.modl.inventairePrincipal.getListeInventaire().size(); i++) {
//			l.get(i).setCenter(this.modl.inventairePrincipal.getListeInventaire().get(i));
//		}

	}


	EventHandler<MouseEvent> infoCraft = 
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			clickInfoCraft(t);
		}
	};

	EventHandler<MouseEvent> detectCraft = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			click(e);
			Craft t =  (Craft) e.getTarget();
			System.out.println(t.afficherMatrice());

		}
	};

	public void cl(Event e) {
		Craft copy = blanc.clone();
		l.get(indextable).setCenter(copy);
		this.modl.ajoutCraftDansTable(blanc);

		if (indextable < 9) {
			indextable++;
		}
		curseur();

	}

	private void testcraftcontroller() {
		for (int i = 0; i < this.modl.tableCraft.length; i++) {
			for (int j = 0; j < this.modl.tableCraft.length; j++) {

				Craft resultat=this.modl.testCraft(i,j);
				resultat.setEstTrouve(true);
				System.out.println(resultat.getName());
				craftFinal.setCenter(resultat.clone());
				
				this.modl.suppressionTable();
				for (int k = 0; k < l.size(); k++) {
					l.get(k).setCenter(null);
				}
				
				if (!listeCraftInventaire.contains(resultat.getName()) && resultat.getName() != "non trouvé") {
					listeBlocInv.get(indexInv).setCenter(resultat.clone());
					indexInv++;
					score++;
					affScore.setText(score + "/" + this.modl.getInventairePrincipal().getListeInventaire().size());

					int prog = ((score*100)/scoreFinal)/100;
					pg.setProgress(prog);
					System.out.println(prog);

				}else {
					System.out.println("h");
				}
				listeCraftInventaire.add(resultat.getName());

			}
		}

	}


	public void clickInfoCraft(MouseEvent e) {
		Craft currentCraft = (Craft) e.getTarget();
		nomCraft.setText(currentCraft.getName());
		System.out.println(currentCraft.getName());

	}

	public void click(MouseEvent e) {
		if(e.getTarget().getClass()==Craft.class && !flecheH ) {
			this.modl.ajoutCraftDansTable((Craft) e.getTarget()); 
			Craft copyCraft = (Craft) e.getTarget();
			l.get(indextable).setCenter(copyCraft.clone());

			if (indextable < 9) {
				indextable++;
			}
			System.out.println(indextable);

			curseur();

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
		} catch (ClassCastException e2) {
			// TODO: handle exception
		}
	}


	public void crafting() {
		indextable = 0;
		curseur();
		testcraftcontroller();
		this.modl.toString();
		this.modl.suppressionTable();
		for (int i = 0; i < l.size(); i++) {
			l.get(i).setCenter(blanc.clone());
		}


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

		music.play();
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
