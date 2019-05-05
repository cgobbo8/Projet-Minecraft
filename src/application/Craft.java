package application;

import java.util.ArrayList;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Craft extends ImageView implements Cloneable{

	private String nom;
	public String ip;
	private ArrayList<Craft> parents;
	private ArrayList<Craft> enfants;
	private Craft[][] matriceCraft = new Craft[3][3];
	private Image img;
	enum Type {BLOC,COMBAT,OUTIL,NOURRITURE,REDSTONE,DECO,BASE};
	private Type type;
	private boolean estTrouve = false;
	private Inventaire inv;


	public Craft(String n, String ip, Craft[][] c, Type t,boolean et) {
		this.ip = ip;
		this.img = new Image(getClass().getResourceAsStream(ip));
		this.setImage(this.img);
		this.setScaleX(2);
		this.setScaleY(2);
		Tooltip tooltip = new Tooltip(n);
		Tooltip.install(this, tooltip);
		this.setId("cur");



		this.nom = n;
		this.type = t;
		this.matriceCraft = c;
		this.estTrouve = et;
		this.parents = new ArrayList<Craft>();
		this.enfants = new ArrayList<Craft>();

		//creation listes parents/enfants
		try {
			for(int i = 0 ; i < this.matriceCraft.length ; i++) {
				for(int j = 0 ; j < this.matriceCraft.length ; j++) {
					if(!this.parents.contains(this.matriceCraft[i][j])) {
						this.parents.add(this.matriceCraft[i][j]);
					}
				}
			}
			for (int i = 0 ; i < this.parents.size() ; i++){
				this.parents.get(i).enfants.add(this);
			}

			System.out.println(this.inv.toString());

		} catch (NullPointerException e) {

		}


	}

	public boolean egaliteCraft(Craft a) {
		boolean e = true;
		if (this.nom != a.nom) {
			e = false;
		}
		return e;
	}

	public Craft clone() {
		return new Craft(this.nom, this.ip, this.matriceCraft, this.type, this.estTrouve);
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
	
	public String afficherMatrice() {
		String s = "";
		
		for (int i = 0; i < matriceCraft.length; i++) {
			for (int j = 0; j < matriceCraft.length; j++) {
				s+= matriceCraft[i][j].getName() + " ";
			}
		}
		return s;
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
