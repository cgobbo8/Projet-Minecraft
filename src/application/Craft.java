package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Craft extends ImageView implements Cloneable{

	private String nom;
	public String ip;
	private HashMap<String,Craft> parents;
	private HashMap<String,Craft> enfants;
	private Craft[][] matriceCraft = new Craft[3][3];
	private Image img;
	enum Type {BLOC,COMBAT,OUTIL,NOURRITURE,REDSTONE,DECO,BASE};
	private Type type;
	private boolean estTrouve = false;
	private Inventaire inv;


	public Craft(String n, String ip, Craft[][] c, Type t, Inventaire inv,boolean et) {
		this.ip = ip;
		this.img = new Image(getClass().getResourceAsStream(ip));
		this.setImage(this.img);
		this.setScaleX(2);
		this.setScaleY(2);
		Tooltip tooltip = new Tooltip(n);
		Tooltip.install(this, tooltip);
		this.setId("cur");



		this.inv = inv;
		this.nom = n;
		this.type = t;
		this.matriceCraft = c;
		this.estTrouve = et;
		this.parents = new HashMap<String, Craft>();
		this.enfants = new HashMap<String, Craft>();

		//creation hashmap parents
		try {
			for(int i = 0 ; i < this.matriceCraft.length ; i++) {
				for(int j = 0 ; j < this.matriceCraft.length ; j++) {
					if(!this.parents.containsKey(this.matriceCraft[i][j].getName())) {
						this.parents.put(this.matriceCraft[i][j].getName(), this.matriceCraft[i][j]);
						this.parents.get(this.matriceCraft[i][j].getName()).enfants.put(this.getName(), this);
					}
				}
			}
			this.inv.addCraft(this);
	
			
			for(String key : this.parents.keySet()) {
				Craft cparent = this.parents.get(key);
				for(String keyEnf : cparent.enfants.keySet()) {
					Craft cenf = cparent.enfants.get(keyEnf);
					System.out.println("Enfants de  " + cparent.getName() + " : " + cenf.getName());					
				}				
			}
			
			
			
			
		} catch (NullPointerException e) {

		}
		
		System.out.println("Parents de  " + this.getName() + " : " + this.parents);

	}




	public Craft clone() {
		return new Craft(this.nom, this.ip, this.matriceCraft, this.type, this.inv, this.estTrouve);
	}


	public String getName() {
		return this.nom;
	}

	public void setName(String s) {
		this.nom = s;
	}

	public HashMap<String, Craft> getParents() {
		return this.parents;
	}

	public void addParents(Craft c) {
		this.parents.put(c.getName(),c);
	}

	public HashMap<String,Craft> getEnfants(){
		return this.enfants;
	}

	public void addEnfants(Craft c) {
		this.enfants.put(c.getName(),c);
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
