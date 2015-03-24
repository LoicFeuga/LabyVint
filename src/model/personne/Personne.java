package model.personne;

import java.awt.Rectangle;

import model.implement.IDeplacable;
import model.objet.ObjetCollision;

public class Personne extends ObjetCollision implements IDeplacable {
	/**
	 * vitesse de déplacement
	 */
	private int vitesse;
	/**
	 * nom
	 */
	private String pseudo;

	/**
	 * Constructeur de personne
	 */
	public Personne(int vitesse, String pseudo, Rectangle hitBox) {
		super(hitBox,"Joueur");
		this.vitesse = vitesse;
		this.pseudo = pseudo;
		
	}

	/**
	 * getteur de vitesse
	 * 
	 * @return vitesse
	 */
	public int getVitesse() {
		return vitesse;
	}

	/**
	 * setteur de vitesse
	 * 
	 * @param vitesse
	 */

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * getteur de nom
	 * 
	 * @return nom
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * setteur de nom
	 * 
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public void deplacer(Object aDirection_direction) {
		// TODO Auto-generated method stub

	}

}