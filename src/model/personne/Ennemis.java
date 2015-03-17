package model.personne;

import model.Direction;

public class Ennemis extends Personne {

	/**
	 * Constructeur de Ennemis
	 * @param vitesse
	 * @param nom
	 */
	public Ennemis(int vitesse, String nom) {
		super(vitesse, nom);
	}

	/**
	 * deplacement de ennemis
	 */
	public void deplacer(Direction direction) {
		throw new UnsupportedOperationException();
	}
}