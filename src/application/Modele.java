package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import application.Craft.Type;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Modele extends Observable {


	MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("ressources/music2.mp3").toExternalForm()));
	Craft[][] tableCraft = new Craft[3][3];
	Inventaire inventairePrincipal = new Inventaire();
	File fichierCraft = new File("Projet-Minecraft/testest.csv");

	public Modele(Inventaire i) {


	}

	public void serialisation() throws IOException {

		try(FileReader fr = new FileReader("craft.txt")) {
			BufferedReader br = new BufferedReader(fr);
			for (String line = br.readLine(); line !=null; line = br.readLine()) {
				String[] parts = line.split(";");
				String[] crf = parts[2].split("/");

				this.inventairePrincipal.addCraft(new Craft(parts[0],parts[1],new Craft[][] {{this.inventairePrincipal.getInventaire().get(crf[0]),this.inventairePrincipal.getInventaire().get(crf[1]),this.inventairePrincipal.getInventaire().get(crf[2])},{this.inventairePrincipal.getInventaire().get(crf[3]),this.inventairePrincipal.getInventaire().get(crf[4]),this.inventairePrincipal.getInventaire().get(crf[5])},{this.inventairePrincipal.getInventaire().get(crf[6]),this.inventairePrincipal.getInventaire().get(crf[7]),this.inventairePrincipal.getInventaire().get(crf[8])}},Type.valueOf(parts[3]),Boolean.getBoolean(parts[4])));

			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}

	public Craft[][] getTableCraft() {
		return tableCraft;
	}



	public String toString() {
		String s = "";
		for (int i = 0; i < tableCraft.length; i++) {
			for (int j = 0; j < tableCraft.length; j++) {
				try {
					s+=tableCraft[i][j].getName() + " ";
				} catch (NullPointerException e) {
					// TODO: handle exception
				}

			}
		}

		return s;
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

	public ArrayList<Integer> estDansTable(Craft c){
		ArrayList<Integer> objet = new ArrayList<Integer>();
		for( int i = 0 ; i < this.tableCraft.length ; i++){
			for(int j = 0 ; j < this.tableCraft.length ; j++){
				if(this.tableCraft[i][j].getName() == c.getName()){
					objet.add(i);
					objet.add(j);
					return objet;
				}
			}
		}
		return null;
	}

	public void suppressionTable(){ //Int i compris entre 0 et 8
		this.tableCraft = new Craft[3][3];
	}

	public Craft testCraft(int i,int j){

		for (int k2 = 0; k2 < this.tableCraft[i][j].getEnfants().size(); k2++) {
			if(Arrays.deepEquals(this.tableCraft,this.tableCraft[i][j].getEnfants().get(k2).getMatrice())) {
				System.out.println("trouvé");
				return this.tableCraft[i][j].getEnfants().get(k2);
			}
		}

		Craft nofind = new Craft("non trouvé", "cross2.png", new Craft[3][3], Type.BASE, true );
		return nofind;
	}

	public void setTableCraft(Craft[][] tableCraft) {
		this.tableCraft = tableCraft;
	}

	public Inventaire getInventairePrincipal() {
		return inventairePrincipal;
	}

}