package application;

import java.util.ArrayList;

public class Inventaire {
	private ArrayList<Craft> inventaire;
	private ArrayList<Craft> bloc;
	private ArrayList<Craft> combat;
	private ArrayList<Craft> outil;
	private ArrayList<Craft> nourriture;
	private ArrayList<Craft> redstone;
	private ArrayList<Craft> deco;
	private ArrayList<Craft> base;
	
	public Inventaire() {
		this.inventaire = new ArrayList<>();
		this.bloc = new ArrayList<>();
		this.combat = new ArrayList<>();
		this.outil = new ArrayList<>();
		this.nourriture = new ArrayList<>();
		this.redstone = new ArrayList<>();
		this.deco = new ArrayList<>();
		this.base = new ArrayList<>();
		
		
		}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < this.inventaire.size(); i++) {
			s+= this.inventaire.get(i).getName();
		}
		return s;
	}
	public void addCraft(Craft a) {
		
		this.inventaire.add(a);
		
		switch(a.getType()) {
		  case BLOC:
			this.bloc.add(a);
		    break;
		  case COMBAT:
		    this.combat.add(a);
		    break;
		  case OUTIL:
			this.outil.add(a);
			break;
		  case NOURRITURE:
			this.nourriture.add(a);
			break;
		  case REDSTONE:
			this.redstone.add(a);
			break;
		  case DECO:
			this.deco.add(a);
			break;
		  case BASE:
		    this.base.add(a);
			break;
		  default:
			break;
		    
		}
	}
	
	
	

}
