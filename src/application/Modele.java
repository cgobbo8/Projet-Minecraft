package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import application.Craft.Type;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Modele extends Observable {


	MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("ressources/music2.mp3").toExternalForm()));
	Craft[][] tableCraft = new Craft[3][3];
	Inventaire inventairePrincipal;
	File fichierCraft = new File("Projet-Minecraft/testest.csv");

	public Modele(Inventaire i) {
		
		
	}
	
	public void serialisation() throws IOException {
		FileReader fr = new FileReader(new File("craft.txt"));
        BufferedReader br = new BufferedReader(fr);
        for (String line = br.readLine(); line !=null; line = br.readLine()) {
        	String[] parts = line.split(";");
        	String[] crf = parts[2].split("/");
        	
        	Craft c = new Craft(parts[0],parts[1],new Craft[][] {{this.inventairePrincipal.getInventaire().get(crf[0]),this.inventairePrincipal.getInventaire().get(crf[0]),this.inventairePrincipal.getInventaire().get(crf[0])},{this.inventairePrincipal.getInventaire().get(crf[0]),this.inventairePrincipal.getInventaire().get(crf[0]),this.inventairePrincipal.getInventaire().get(crf[0])},{this.inventairePrincipal.getInventaire().get(crf[0]),this.inventairePrincipal.getInventaire().get(crf[0]),this.inventairePrincipal.getInventaire().get(crf[0])}},Type.valueOf(crf[3]),Boolean.getBoolean(crf[4]));
        	this.inventairePrincipal.getInventaire().put(crf[0], c);
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
		
		for (int j2 = 0; j2 < this.tableCraft.length; j2++) {
			for (int k = 0; k < this.tableCraft.length; k++) {
				for (int k2 = 0; k2 < this.tableCraft[i][j].getEnfants().size(); k2++) {
					if(Arrays.deepEquals(this.tableCraft,this.tableCraft[j2][k].getEnfants().get(k2).getMatrice())) {
						return this.tableCraft[i][j].getEnfants().get(k);
					}
				}
			}
		}
		Craft nofind = new Craft("non trouvé", "cross2.png", new Craft[3][3], Type.BASE, true );
		return nofind;
	}

	public void setTableCraft(Craft[][] tableCraft) {
		this.tableCraft = tableCraft;
	}
	
	
	//	public static void main(String[] args) {
	//		
	//		Modele mo = new Modele(new Inventaire());
	//		//craft de base
	//		Craft blanc = new Craft("blanc", "/Projet-Minecraft/crafts/apple", new Craft [][] {{null,null,null},{null,null,null},{null,null,null}} , Type.BASE, mo.inventairePrincipal,true) ;
	//		Craft apple = new Craft("apple", "/Projet-Minecraft/crafts/apple", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.NOURRITURE, mo.inventairePrincipal,true) ;
	//		Craft diamond = new Craft("diamond", "/Projet-Minecraft/crafts/diamond", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BASE, mo.inventairePrincipal,true) ;
	//		Craft gold = new Craft("gold", "/Projet-Minecraft/crafts/gold_ingot", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BASE, mo.inventairePrincipal,true) ;
	//		Craft iron = new Craft("iron", "/Projet-Minecraft/crafts/iron_ingot", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BASE, mo.inventairePrincipal,true) ;
	//		Craft cobble = new Craft("cobblestone", "/Projet-Minecraft/crafts/cobblestone", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BLOC, mo.inventairePrincipal,true) ;
	//		Craft redstone = new Craft("restone", "/Projet-Minecraft/crafts/redstone_dust", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BASE, mo.inventairePrincipal,true) ;
	//		Craft gunpowder = new Craft("gunpowder", "/Projet-Minecraft/crafts/gunpowder", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BASE, mo.inventairePrincipal,true) ;
	//		Craft stick = new Craft("stick", "/Projet-Minecraft/crafts/stick", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BASE, mo.inventairePrincipal,true) ;
	//		
	//		
	//		Craft planche = new Craft("planche", "/Projet-Minecraft/crafts/planks_oak", new Craft [][] {{blanc,blanc,blanc},{blanc,blanc,blanc},{blanc,blanc,blanc}} , Type.BLOC, mo.inventairePrincipal,false) ;
	//		Craft craftingTable = new Craft("crafting Table", "/Projet-Minecraft/crafts/crafting_table_front", new Craft [][] {{planche,planche,blanc},{planche,planche,blanc},{blanc,blanc,blanc}} , Type.BASE, mo.inventairePrincipal,false) ;
	//		Craft four = new Craft("four", "/Projet-Minecraft/crafts/furnace_front_on", new Craft [][] {{cobble,cobble,cobble},{cobble,blanc,cobble},{cobble,cobble,cobble}} , Type.BASE, mo.inventairePrincipal,false) ;
	//		Craft diamondSword = new Craft("diamond Sword", "/Projet-Minecraft/crafts/diamond_sword", new Craft [][] {{blanc,diamond,blanc},{blanc,diamond,blanc},{blanc,stick,blanc}} , Type.COMBAT, mo.inventairePrincipal,false) ;
	//		Craft diamondPickaxe = new Craft("diamond Pickaxe", "/Projet-Minecraft/crafts/diamond_pickaxe", new Craft [][] {{diamond,diamond,diamond},{blanc,stick,blanc},{blanc,stick,blanc}} , Type.OUTIL, mo.inventairePrincipal,false) ;
	//		
	//		
	//	}

	public Inventaire getInventairePrincipal() {
		return inventairePrincipal;
	}

}