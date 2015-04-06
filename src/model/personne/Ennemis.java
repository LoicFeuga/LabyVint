package model.personne;

import java.awt.Rectangle;

import model.Direction;

public class Ennemis extends Personne {

	/**
	 * Constructeur de Ennemis
	 * @param vitesse
	 * @param nomType
	 */
	public Ennemis(int vitesse, Rectangle hitBox) {
		super(vitesse, hitBox);
		super.nomType = Type.Ennemis.name();
	}

	/**
	 * deplacement de ennemis
	 */
	public void deplacer(Direction direction) {
		throw new UnsupportedOperationException();
	}
}