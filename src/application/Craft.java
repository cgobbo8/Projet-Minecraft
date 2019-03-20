package application;

import java.util.ArrayList;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Craft extends Pane{
	
	private String nom;
	private ArrayList<Craft> parents;
	private ArrayList<Craft> enfants;
	private Craft[][] matriceCraft = new Craft[3][3];
	private Image img;
	enum Type {BLOC,COMBAT,OUTIL,NOURRITURE,REDSTONE,DECO,BASE};
	private Type type;
	
	
	public Craft(String n, String ip, Craft[][] c, Type t) {
		this.nom = n;
		this.type = t;
		this.img = new Image(ip);
		this.matriceCraft = c;
		
		for(int i = 0 ; i < this.matriceCraft.length ; i++) {
			for(int j = 0 ; j < this.matriceCraft.length ; i++) {
				if(this.matriceCraft[i][j] != null && !this.parents.contains(this.matriceCraft[i][j])) {
					this.parents.add(this.matriceCraft[i][j]);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String getName() {
		return this.nom;
	}
	
	public void setName(String s) {
		this.nom = s;
	}
	
	public ArrayList<Craft> getParents() {
		return this.parents;
	}
	
	public void addParents(Craft c) {
		this.parents.add(c);
	}
	
	public ArrayList<Craft> getEnfants(){
		return this.enfants;
	}
	
	public void addEnfants(Craft c) {
		this.enfants.add(c);
	}
	
	public Craft[][] getMatrice(){
		return this.matriceCraft;
	}
	
	public Type getType() {
		return this.type;
	}

}
