package application;

import java.util.Observable;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Modele extends Observable {
	
	
	MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("ressources/music2.mp3").toExternalForm()));
	Craft[][] tableCraft = new Craft[3][3];
	Inventaire inventairePrincipal = new Inventaire();

	public Modele() {
		
	}

	public void ajoutCraftDansTable(Craft c){
		int i = 0;
		int j = 0;
		while(this.tableCraft[i][j] != null){
			if(i <= 2){
				if(j < 2){
					j++;
				}
				else{
					j = 0;
					i++;
				}
			}
		}
		if(i < 3 && j < 3){
			this.tableCraft[i][j] = c;
		}
	}

	public List<Object> estDansTable(Craft c){
		List<Object> objet = new List<Object>();
		for( int i = 0 ; i < this.tableCraft.length ; i++){
			for(int j = 0 ; j < this.tableCraft.length ; j++){
				if(this.tableCraft[i][j].getName() == c.getName()){
					objet.add(true);
					objet.add(i);
					objet.add(j);
					return objet;
				}
			}
		}
		return null;
	}

	public void suppressionTable(Craft c){
		if(estDansTable() != null){
			List<Objet> objet = estDansTable(c);
			if(objet.get(0)){
				this.tableCraft[objet.get(1)][objet.get(2)] = null;
			}
		}
	}

	public Craft testCraft(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < this.tableCraft[i][j].getEnfants().size(); k++){
					if(this.tableCraft.equals(this.tableCraft[i][j].getEnfants().get(k).getMatrice())){
						this.tableCraft[i][j].getEnfants().get(k).setEstTrouve(true);
						return this.tableCraft[i][j].getEnfants().get(k);
					}
				}
			}
		}
		return null;
	}
	
	
	
}