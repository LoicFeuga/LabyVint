package model.objet;

import java.awt.Rectangle;

import model.implement.IRamassable;

public class Objet extends ObjetCollision implements IRamassable {
	

	protected String nom;
	protected boolean estRamassable;
	
	
	public Objet(Rectangle hitBox) {
		super(hitBox);
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * Permet de ramasser l'objet support
	 * 
	 */
	public Objet ramasser() {
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