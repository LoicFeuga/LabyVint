package model.personne;

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
	private String nom;

	/**
	 * Constructeur de personne
	 */
	public Personne(int vitesse, String nom) {
		super();
		this.vitesse = vitesse;
		this.nom = nom;
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
	public String getNom() {
		return nom;
	}

	/**
	 * setteur de nom
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void deplacer(Object aDirection_direction) {
		// TODO Auto-generated method stub

	}

}