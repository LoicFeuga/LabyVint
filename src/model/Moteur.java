package model;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Vector;

import model.objet.ObjetCollision;
import model.personne.Joueur;
import model.personne.Personne;
import controleur.Controleur;

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
	 * 
			//"deplacer"->"point"->Point(x,y);
			//			->"id"->id
	 */
	public void update() {
		//Si le joueur peux bouger

		if(true/*carte.canMoveToNewDirection(joueur.getHitBox())*/){
			HashMap<String, HashMap<String, Object> >send = new HashMap();
			HashMap<String, Object> description = new HashMap();
			
			description.put("point", joueur.getDefaultPosition());
			description.put("id",joueur.getId());
			
			send.put("deplacer", description);
			setChanged();
			notifyObservers(send);
		}else{
			joueur.annulerDeplacement();
		}
	}


}