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
		Rectangle hitBox = new Rectangle();
		int x = hitBoxJoueur.x, y = hitBoxJoueur.y;
		if(Direction.NORD.equals(direction)){
			x += hitBoxJoueur.width/2;
			y += hitBoxJoueur.height/2 - vit;
			hitBox.setBounds(x, y, 1, hitBoxJoueur.height/2);
		}
		else if(Direction.SUD.equals(direction)){
			x += hitBoxJoueur.width/2;
			y += vit;
			hitBox.setBounds(x, y, 1, hitBoxJoueur.height/2);
		}
		else if(Direction.EST.equals(direction)){
			y += hitBoxJoueur.height/2;
			x +=  vit;
			hitBox.setBounds(x, y, hitBoxJoueur.width/2, 1);
		}
		else{
			y += hitBoxJoueur.height/2;
			x +=  hitBoxJoueur.width/2 - vit;
			hitBox.setBounds(x, y, hitBoxJoueur.width/2, 1);
		}
		
		joueur.setHitBox(hitBox);
		boolean r =  carte.estEnCollision(joueur);
		joueur.setHitBox(hitBoxJoueur);
		return r;
	}
}