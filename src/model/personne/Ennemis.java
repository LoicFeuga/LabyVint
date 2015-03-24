package model.personne;

import java.awt.Rectangle;

import model.Direction;

public class Ennemis extends Personne {

	/**
	 * Constructeur de Ennemis
	 * @param vitesse
	 * @param nom
	 */
	public Ennemis(int vitesse, Rectangle hitBox) {
		super(vitesse, "Ennemis", hitBox);
	}

	/**
	 * deplacement de ennemis
	 */
	public void deplacer(Direction direction) {
		throw new UnsupportedOperationException();
	}
}