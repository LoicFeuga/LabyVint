package model;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import model.objet.ObjetCollision;
import model.personne.Joueur;

public class Moteur extends Observable {

	//private ArrayList<Personne> listePersonne = new ArrayList<Personne>();
	private Joueur joueur;
	private Carte carte;

	public Moteur(ArrayList<ObjetCollision> list, Joueur joueur) {
		carte = new Carte(list);
		//listePersonne = new ArrayList();
		this.joueur = joueur;
		//listePersonne.add(j);
	}

	/**
	 * @return the joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Permet de mettre a jour la vue.
	 * 
	 * HashMap : "deplacer"->Point
	 *                 "id"->int
	 */
	public void update() {
		HashMap<String, HashMap<String, Object>> send = new HashMap<>();
		HashMap<String, Object> description = new HashMap<>();
			
		description.put("point", joueur.getDefaultPosition());
		description.put("id",joueur.getId());
		
		send.put("deplacer", description);
		
		setChanged();
		notifyObservers(send);
	}
	
	public void moveJoueur(Direction direction){
		
		if( joueurEstDeplacable(direction) ){
			joueur.deplacer(direction);
			update();
		}
	}

	private boolean joueurEstDeplacable(Direction direction){
		if( direction == null || Direction.NONE.equals(direction)){
			return false;
		}
		
		int vit = joueur.getVitesse();
		Rectangle hitBoxJoueur =  joueur.getHitBox();
		Rectangle hitBox = (Rectangle) hitBoxJoueur.clone();
		if(Direction.NORD.equals(direction)){
			hitBox.setLocation(hitBoxJoueur.x, hitBoxJoueur.y - vit);
		}
		else if(Direction.SUD.equals(direction)){
			hitBox.setLocation(hitBoxJoueur.x, hitBoxJoueur.y + vit );
		}
		else if(Direction.EST.equals(direction)){
			hitBox.setLocation(hitBoxJoueur.x + vit, hitBoxJoueur.y);
		}
		else{
			hitBox.setLocation(hitBoxJoueur.x - vit, hitBoxJoueur.y);
		}
		
		joueur.setHitBox(hitBox);
		boolean r =  carte.estEnCollision(joueur);
		joueur.setHitBox(hitBoxJoueur);
		return r;
	}
}