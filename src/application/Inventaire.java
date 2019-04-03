package application;

import java.util.ArrayList;

import application.Craft.Type;

public class Inventaire {
	private ArrayList<Craft> inventaire;
	private ArrayList<Craft> bloc;
	private ArrayList<Craft> combat;
	private ArrayList<Craft> outil;
	private ArrayList<Craft> nourriture;
	private ArrayList<Craft> redstone;
	private ArrayList<Craft> deco;
	private ArrayList<Craft> base;
	
	public Inventaire(ArrayList<Craft> liste) {
		this.inventaire = liste;
		this.bloc = new ArrayList<>();
		this.combat = new ArrayList<>();
		this.outil = new ArrayList<>();
		this.nourriture = new ArrayList<>();
		this.redstone = new ArrayList<>();
		this.deco = new ArrayList<>();
		this.base = new ArrayList<>();
		
		
		for (int i = 0;i<inventaire.size();i++) {
			Type leType = inventaire.get(i).getType();
			switch(leType) {
			  case BLOC:
				this.bloc.add(inventaire.get(i));
			    break;
			  case COMBAT:
			    this.combat.add(inventaire.get(i));
			    break;
			  case OUTIL:
				this.outil.add(inventaire.get(i));
				break;
			  case NOURRITURE:
				this.nourriture.add(inventaire.get(i));
				break;
			  case REDSTONE:
				this.redstone.add(inventaire.get(i));
				break;
			  case DECO:
				this.deco.add(inventaire.get(i));
				break;
			  case BASE:
			    this.base.add(inventaire.get(i));
				break;
			  default:
				break;
			    
			}
		}
	}
	public void addCraft(Craft a) {
		
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
