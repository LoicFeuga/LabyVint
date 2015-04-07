package model.personne;

import java.awt.Rectangle;

import model.Direction;
import model.implement.IDeplacable;
import model.objet.ObjetCollision;

public class Personne extends ObjetCollision implements IDeplacable {

	public static final int DEFAULT_VITESSE = 5;

	/**
	 * vitesse de déplacement
	 */
	private int vitesse;

	/**
	 * Constructeur de personne
	 */
	public Personne(int vitesse, Rectangle hitBox) {
		super(hitBox, Type.Personne.name());
		this.vitesse = vitesse;

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

	@Override
	public void deplacer(Direction direction) {
		if (direction == Direction.EST) {
			hitBox.x += vitesse;
		} else if (direction == Direction.OUEST) {
			hitBox.x -= vitesse;
		} else if (direction == Direction.NORD) {
			hitBox.y -= vitesse;
		} else if (direction == Direction.SUD) {
			hitBox.y += vitesse;
		}
	}

}