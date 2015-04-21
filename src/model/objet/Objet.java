package model.objet;

import java.awt.Rectangle;

import model.Moteur;
import model.implement.IRamassable;

public class Objet extends ObjetCollision implements IRamassable {
	
	protected boolean estRamassable;
	
	
	public Objet(Rectangle hitBox, String nom) {
		super(hitBox, nom);
		this.nomType = nom;
	}

	public String getPseudo() {
		return this.nomType;
	}

	/**
	 * Permet de ramasser l'objet support
	 * 
	 */
	public Objet onRamasser() {
		Moteur moteur = Moteur.getMoteur();
		moteur.removeObjet(this);
		return this;		
	}

	/**
	 * Permet de savoir si un objet est ramassable
	 * 
	 */
	public boolean estRamassable() {
		return estRamassable;
	}
}