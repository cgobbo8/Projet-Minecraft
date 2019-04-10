package application;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Craft extends ImageView{
	
	private String nom;
	private HashMap<Craft[][],Craft> parents;
	private HashMap<Craft[][],Craft> enfants;
	private Craft[][] matriceCraft = new Craft[3][3];
	private Image img;
	enum Type {BLOC,COMBAT,OUTIL,NOURRITURE,REDSTONE,DECO,BASE};
	private Type type;
	private boolean estTrouve = false;
	
	
	public Craft(String n, String ip, Craft[][] c, Type t, Inventaire inv,boolean et) {
		this.img = new Image(getClass().getResourceAsStream(ip));
		this.setImage(this.img);
		this.setScaleX(2);
		this.setScaleY(2);
		Tooltip tooltip = new Tooltip(n);
		Tooltip.install(this, tooltip);
		
		this.nom = n;
		this.type = t;
		this.matriceCraft = c;
		this.estTrouve = et;
		
		for(int i = 0 ; i < this.matriceCraft.length ; i++) {
			for(int j = 0 ; j < this.matriceCraft.length ; i++) {
				if(this.matriceCraft[i][j] != null && !this.parents.containsKey(this.matriceCraft[i][j].getMatrice())) {
					this.parents.put(this.matriceCraft[i][j].getMatrice(),this.matriceCraft[i][j]);
				}
			}
		}
		inv.addCraft(this);
	}
	

	public String getName() {
		return this.nom;
	}
	
	public void setName(String s) {
		this.nom = s;
	}
	
	public HashMap<Craft[][], Craft> getParents() {
		return this.parents;
	}
	
	public void addParents(Craft c) {
		this.parents.put(c.getMatrice(),c);
	}
	
	public HashMap<Craft[][],Craft> getEnfants(){
		return this.enfants;
	}
	
	public void addEnfants(Craft c) {
		this.enfants.put(c.getMatrice(),c);
	}
	
	public Craft[][] getMatrice(){
		return this.matriceCraft;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public boolean getEstTrouve() {
		return this.estTrouve;
	}
	
	public void setEstTrouve(boolean b) {
		this.estTrouve = b;
	}

}
