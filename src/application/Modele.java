package application;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import shifumi.String;

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

	public List estDansTable(Craft c){
		List objet = new List();
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
		if(estDansTable(c) != null){
			List objet = estDansTable(c);
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
	
	public static void main(String[] args) {
		
		Modele mo = new Modele();
		//craft de base
		Craft blanc = new Craft("blanc", "\Projet-Minecraft\crafts\apple", [[blanc,blanc,blanc],[blanc,blanc,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		Craft apple = new Craft("apple", "\Projet-Minecraft\crafts\apple", [[blanc,blanc,blanc],[blanc,apple,blanc],[blanc,blanc,blanc]], NOURRITURE, this.inventairePrincipal,true);
		Craft diamond = new Craft("diam's", "\Projet-Minecraft\crafts\diamond", [[blanc,blanc,blanc],[blanc,diamondc,blanc],[blanc,blanc,blanc]] , base, this.inventairePrincipal,true);
		Craft gold = new Craft("gold", "\Projet-Minecraft\crafts\gold_ingot", [[blanc,blanc,blanc],[blanc,gold,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		Craft iron = new Craft("iron", "\Projet-Minecraft\crafts\iron_ingot", [[blanc,blanc,blanc],[blanc,iron,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		Craft cobble = new Craft("cobble", "\Projet-Minecraft\crafts\cobblestone", [[blanc,blanc,blanc],[blanc,cobble,blanc],[blanc,blanc,blanc]] , BLOC, this.inventairePrincipal,true);
		Craft redstone = new Craft("redstone", "\Projet-Minecraft\crafts\redstone_dust", [[blanc,blanc,blanc],[blanc,redstone,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		Craft gunpowder = new Craft("gunpowder", "\Projet-Minecraft\crafts\apple", [[blanc,blanc,blanc],[blanc,blanc,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		Craft coal = new Craft("coal", "\Projet-Minecraft\crafts\coal", [[blanc,blanc,blanc],[blanc,coal,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		Craft wood = new Craft("wood", "\Projet-Minecraft\crafts\log_oak", [[blanc,blanc,blanc],[blanc,wood,blanc],[blanc,blanc,blanc]] , BLOC, this.inventairePrincipal,true);
		Craft string = new Craft("string", "\Projet-Minecraft\crafts\string", [[blanc,blanc,blanc],[blanc,string,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		Craft stick = new Craft("stick", "\Projet-Minecraft\crafts\stick", [[blanc,blanc,blanc],[blanc,stick,blanc],[blanc,blanc,blanc]] , BASE, this.inventairePrincipal,true);
		
		
		Craft planche = new Craft("planche", "\Projet-Minecraft\crafts\planks_oak", [[blanc,blanc,blanc],[blanc,wood,blanc],[blanc,blanc,blanc]] , BLOC, this.inventairePrincipal,false);
		Craft craftingTable = new Craft("crafting table", "\Projet-Minecraft\crafts\crafting_table_front", [[planche,planche,blanc],[planche,planche,blanc],[blanc,blanc,blanc]] , BLOC, this.inventairePrincipal,false);
		Craft four = new Craft("four", "\Projet-Minecraft\crafts\furnace_front_on", [[cobble,cobble,cobble],[cobble,blanc,cobble],[cobble,cobble,cobble]] , BLOC, this.inventairePrincipal,false);
		Craft diamondSword = new Craft("diamond Sword", "\Projet-Minecraft\crafts\diamond_sword", [[blanc,diamond,blanc],[blanc,diamond,blanc],[blanc,stick,blanc]] , COMBAT, this.inventairePrincipal,false);
		Craft diamondPickaxe = new Craft("diamond Pickaxe", "\Projet-Minecraft\crafts\diamond_pickaxe", [[diamond,diamond,diamond],[blanc,stick,blanc],[blanc,stick,blanc]] , OUTIL, this.inventairePrincipal,false);
		
	}
	
}